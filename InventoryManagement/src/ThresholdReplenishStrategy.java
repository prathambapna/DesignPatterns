import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class ThresholdReplenishStrategy implements ReplenishStrategy{

    private final ConcurrentHashMap<Integer,ReplenishRule> thresholdMap;
    private final ConcurrentHashMap<Integer,Integer>inTransitStock;

    public ThresholdReplenishStrategy(){
        thresholdMap=new ConcurrentHashMap<>();
        inTransitStock=new ConcurrentHashMap<>();
    }

    public void setOrUpdateRule(int sku, ReplenishRule rule) {
        thresholdMap.put(sku, rule);
    }

    @Override
    public void replenishItems(InventoryManager inventoryManager,int sku,int currQty) {
        ReplenishRule replenishRule=thresholdMap.get(sku);

        // ATOMIC LOCK: Lock this specific SKU to prevent "Double Order" bugs
        inTransitStock.compute(sku, (key, currentInTransit) -> {

            int inTransit = (currentInTransit == null) ? 0 : currentInTransit;

            // 1. Fetch the absolute live physical stock right now
            int livePhysicalStock = inventoryManager.checkStock(sku);

            // 2. Evaluate (Physical + Incoming) against the threshold
            if ((livePhysicalStock + inTransit) <= replenishRule.threshold) {

                System.out.println("\n  >> [PO GENERATED] Dispatching truck with " + replenishRule.batchSize + " units of SKU " + sku);

                // 3. Dispatch the truck asynchronously (doesn't block the main thread)
                dispatchDeliveryTruck(inventoryManager, sku, replenishRule.batchSize);

                // 4. Update state: return the new in-transit value to save it atomically
                return inTransit + replenishRule.batchSize;
            }

            // No order needed, return the existing value unchanged
            return inTransit;
        });
    }

    private void dispatchDeliveryTruck(InventoryManager manager, int sku, int batchSize) {
        CompletableFuture.runAsync(() -> {
            try {
                // Simulate the driving time from the Mother Hub to the Dark Store
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("\n  >> [TRUCK ARRIVED] Unloading " + batchSize + " units of SKU " + sku + "!");

            // 1. Unload the truck: Add items to the physical shelf
            manager.addStock(sku, batchSize);

            // 2. Safely remove these items from the in-transit tracking map
            inTransitStock.compute(sku, (k, val) -> (val == null) ? 0 : val - batchSize);
        });
    }
}

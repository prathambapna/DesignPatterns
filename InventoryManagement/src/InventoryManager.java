import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private final InventoryStore inventoryStore;
    private final List<InventoryObserver>observers=new ArrayList<>();

    public InventoryManager(InventoryStore store){
        this.inventoryStore=store;
    }

    public void addObserver(InventoryObserver observer){
        observers.add(observer);
    }

    public void addStock(int sku, int qty) {
        Product prod = ProductFactory.getProduct(sku);
        inventoryStore.addProduct(prod, qty);
    }

    public int checkStock(int sku) { return inventoryStore.checkStock(sku); }
    public List<Product> getAvailableProducts() { return inventoryStore.listAvailableProducts(); }

    public boolean tryDeductStock(int sku,int requestedQty){
        boolean success = inventoryStore.tryDeductStock(sku, requestedQty);
        if(success){
            int remainingQty=inventoryStore.checkStock(sku);

            for(InventoryObserver observer:observers){
                observer.onStockChanged(sku,remainingQty);
            }
        }
        return success;
    }
}

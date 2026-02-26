import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProximityRoutingStrategy implements OrderRoutingStrategy{

    @Override
    public Order routeAndFulfill(User user, List<DarkStore> nearbyStores) {
        if(nearbyStores.isEmpty())return null;

        Order order=new Order(user);
        Map<Integer,Integer>itemsNeeded=new HashMap<>();
        for(Pair<Product,Integer>item:user.getCart().getItems()){
            itemsNeeded.put(item.getKey().getSku(),item.getValue());
        }

        Map<DarkStore,Map<Integer,Integer>>successfulDeductions=new HashMap<>();
        int partnerId=1;

        for(DarkStore ds:nearbyStores){
            if(itemsNeeded.isEmpty())break;

            List<Integer>fulfilledSkus=new ArrayList<>();
            boolean storeUsed = false;
            successfulDeductions.putIfAbsent(ds, new HashMap<>());

            for (Map.Entry<Integer, Integer> entry : itemsNeeded.entrySet()) {
                int sku = entry.getKey();
                int qtyNeeded = entry.getValue();

                // 1. Atomic Check-And-Act
                if (ds.tryDeductStock(sku, qtyNeeded)) {
                    order.items.add(new Pair<>(ProductFactory.getProduct(sku), qtyNeeded));
                    fulfilledSkus.add(sku);
                    successfulDeductions.get(ds).put(sku, qtyNeeded);
                    storeUsed = true;
                }
            }

            fulfilledSkus.forEach(itemsNeeded::remove);
            if (storeUsed) order.deliveryPartners.add(new DeliveryPartner("Partner" + partnerId++));

        }

        if (!itemsNeeded.isEmpty()) {
            for (Map.Entry<DarkStore, Map<Integer, Integer>> storeEntry : successfulDeductions.entrySet()) {
                DarkStore store = storeEntry.getKey();
                for (Map.Entry<Integer, Integer> itemEntry : storeEntry.getValue().entrySet()) {
                    store.addStock(itemEntry.getKey(), itemEntry.getValue());
                }
            }
            return null; // Order cleanly fails
        }

        order.totalAmount = order.items.stream()
                .mapToDouble(item -> item.getKey().getPrice() * item.getValue())
                .sum();
        return order;
    }
}

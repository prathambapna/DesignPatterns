import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private final DarkStoreManager darkStoreManager;
    private final OrderRoutingStrategy routingStrategy;
    private final List<Order> orders = new ArrayList<>();

    public OrderManager(DarkStoreManager dsManager, OrderRoutingStrategy routingStrategy) {
        this.darkStoreManager = dsManager;
        this.routingStrategy = routingStrategy;
    }

    public boolean placeOrder(User user) {
        List<DarkStore> nearbyStores = darkStoreManager.getNearbyDarkStores(user.x, user.y, 5.0);
        Order order = routingStrategy.routeAndFulfill(user, nearbyStores);

        if (order != null) {
            orders.add(order);
            return true;
        }
        return false;
    }
}

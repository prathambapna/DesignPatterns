import java.util.List;

public interface OrderRoutingStrategy {
    Order routeAndFulfill(User user, List<DarkStore> nearbyStores);
}

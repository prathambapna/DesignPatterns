import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextId=1;
    public final int orderId;
    public final User user;
    public double totalAmount=0.0;
    public final List<Pair<Product,Integer>>items=new ArrayList<>();
    public final List<DeliveryPartner>deliveryPartners=new ArrayList<>();

    public Order(User user){
        this.user=user;
        this.orderId=nextId++;
    }

}

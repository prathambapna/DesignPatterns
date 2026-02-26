import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Pair<Product,Integer>>items=new ArrayList<>();

    public void addItemToCart(int sku,int quantity){
        Product product=ProductFactory.getProduct(sku);
        items.add(new Pair<>(product,quantity));
    }

    public List<Pair<Product,Integer>> getItems(){
        return items;
    }

}

import java.util.List;

public interface InventoryStore {
    void addProduct(Product product,int quantity);
    List<Product>listAvailableProducts();
    int checkStock(int sku);
    boolean tryDeductStock(int sku, int requestedQty);
}

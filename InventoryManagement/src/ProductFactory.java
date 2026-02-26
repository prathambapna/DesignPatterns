public class ProductFactory {
    //in real will fetch from database
    public static Product getProduct(int sku){
        if (sku == 101) return new Product(sku, "Apple", 20);
        if (sku == 102) return new Product(sku, "Banana", 10);
        return new Product(sku, "Item" + sku, 100);
    }
}

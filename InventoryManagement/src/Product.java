public class Product {
    private final int sku;
    private final String name;
    private final double price;

    public Product(int sku,String name , double price){
        this.sku=sku;
        this.name=name;
        this.price=price;
    }

    public int getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

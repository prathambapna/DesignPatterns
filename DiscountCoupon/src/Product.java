public class Product {
    private final String productName;
    private String category;
    private double price;

    public Product(String productName,String category, double price){
        this.price=price;
        this.category=category;
        this.productName=productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getProductName() {
        return productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

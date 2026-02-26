public class User {
    public final String name;
    public final double x,y;
    public final Cart cart;

    public User(String name,double x,double y){
        this.name=name;
        this.x=x;
        this.y=y;
        this.cart=new Cart();
    }

    public Cart getCart() {
        return cart;
    }
}

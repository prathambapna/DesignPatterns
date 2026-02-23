import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // In a real app, DI Framework creates this once.
        CouponManager engine = new CouponManager();

        // Registering active coupons
        engine.registerCoupon(new DiwaliSale("DIWALI25", 10,500,200)); // 10% off
        engine.registerCoupon(new UpiFlatCoupon("ICICIUPI", "@icici", 100,1000)); // Rs 200 flat off
        engine.registerCoupon(new UpiFlatCoupon("PAYTMUPI", "@ptaxis", 120,800)); // Rs 200 flat off

        // Simulating a User Cart
        Cart cart = new Cart();
        cart.addItemToCart(new Product("Headphones", "Electronics", 2000), 1);
        cart.addItemToCart(new Product("Speaker", "Electronics", 3000), 2);
        cart.setUpiId("user@icici");

        System.out.println("Original Cart Total: Rs " + cart.getOriginalTotal());

        // Simulating User selecting checkboxes on the UI
        List<String> selectedByFrontend = Arrays.asList("DIWALI25", "ICICIUPI");

        try {
            double finalTotal = engine.applySelectedCoupons(cart, selectedByFrontend);
            System.out.println("Final Cart Total: Rs " + finalTotal);
        } catch (Exception e) {
            System.err.println("Checkout Failed: " + e.getMessage());
        }
    }
}
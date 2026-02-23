import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItemList=new ArrayList<>();
    private double originalTotal=0.0;
    private double currentTotal=0.0;
    private boolean isLoyaltyMember=false;
    private String upiId;
    private String paymentBank;


    public void addItemToCart(Product product,int quantity){
        CartItem cartItem=new CartItem(product,quantity);
        cartItemList.add(cartItem);
        originalTotal+=cartItem.getItemTotal();
        currentTotal+=cartItem.getItemTotal();
    }

    public void resetToOriginalTotal(){
        this.currentTotal=originalTotal;
    }

    public void applyDiscount(double discount){
        currentTotal-=discount;
        currentTotal=(currentTotal>0)?currentTotal:0;
    }

    public boolean isLoyaltyMember() {
        return isLoyaltyMember;
    }

    public void setLoyaltyMember(boolean loyaltyMember) {
        isLoyaltyMember = loyaltyMember;
    }

    public double getCurrentTotal() {
        return currentTotal;
    }

    public double getOriginalTotal() {
        return originalTotal;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

}

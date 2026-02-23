import java.util.ArrayList;
import java.util.List;

public abstract class Coupon {
    public abstract String getId();
    public abstract String getName();
    public abstract CouponGroup getCouponGroup();
    public abstract DiscountPhase getDiscountPhase();
    public abstract boolean isApplicable(Cart cart);
    public abstract double getDiscount(Cart cart);

    public List<String>getExplicitlyBlockedCouponIds(){
        return new ArrayList<>();
    }
}

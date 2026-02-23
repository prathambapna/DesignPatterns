import java.util.*;

public class CompatibilityMatrix {
    private static final Map<CouponGroup, Set<CouponGroup>>allowedCombinations=new HashMap<>();

    static {
        // Define global stacking rules
        // Store promos can be combined with Payment Offers and Shipping waivers
        allowedCombinations.put(CouponGroup.STORE_PROMO, EnumSet.of(CouponGroup.STORE_PROMO, CouponGroup.PAYMENT_OFFER, CouponGroup.SHIPPING));

        // Payment offers can only be combined with Store Promos (prevents 2 Payment Offers)
        allowedCombinations.put(CouponGroup.PAYMENT_OFFER, EnumSet.of(CouponGroup.STORE_PROMO));

        allowedCombinations.put(CouponGroup.SHIPPING, EnumSet.of(CouponGroup.STORE_PROMO));
    }

    public static boolean canCombineCouponGroup(CouponGroup cg1,CouponGroup cg2){
        return allowedCombinations.getOrDefault(cg1, Collections.emptySet()).contains(cg2);
    }
}

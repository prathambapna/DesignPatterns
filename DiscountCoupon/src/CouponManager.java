import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CouponManager {
    private final Map<String,Coupon>activeCoupons=new ConcurrentHashMap<>();

    public void registerCoupon(Coupon coupon){
        activeCoupons.put(coupon.getId(), coupon);
    }

    public List<Coupon> getEligibleCoupons(Cart cart){
        List<Coupon>eligibleCoupons=new ArrayList<>();
        for(Coupon c: activeCoupons.values()){
            if(c.isApplicable(cart)){
                eligibleCoupons.add(c);
            }
        }
        return eligibleCoupons;
    }

    public double applySelectedCoupons(Cart cart, List<String>selectedCoupons) throws Exception{
        List<Coupon>couponsToApply=new ArrayList<>();
        Set<CouponGroup>couponGroupsApplied=new HashSet<>();

        for(String id:selectedCoupons){
            Coupon coupon=activeCoupons.get(id);

            if(coupon==null){
                throw new Exception("Coupon " + id + " does not exist.");
            }

            if(!coupon.isApplicable(cart)){
                throw new Exception("Coupon " + id + " is not applicable.");
            }

            // Prevent duplicate groups natively (e.g. two Payment Offers)
            if(couponGroupsApplied.contains(coupon.getCouponGroup())){
                // However, we allow multiple STORE_PROMOs if the Matrix permits it
                // (Custom logic can be added here if you want strictly 1 per group)
                if(coupon.getCouponGroup()!=CouponGroup.STORE_PROMO){
                    throw new Exception("Conflict: Cannot apply multiple " + coupon.getCouponGroup() + " coupons.");
                }
            }

            for(Coupon appliedCoupon:couponsToApply){
                if(!CompatibilityMatrix.canCombineCouponGroup(coupon.getCouponGroup(),appliedCoupon.getCouponGroup())){
                    throw new Exception("Global Rule Conflict: " + coupon.getCouponGroup() + " cannot stack with " + appliedCoupon.getCouponGroup());
                }

                if(coupon.getExplicitlyBlockedCouponIds().contains(appliedCoupon.getId()) ||
                appliedCoupon.getExplicitlyBlockedCouponIds().contains(coupon.getId())){
                    throw new Exception("Specific Rule Conflict: " + coupon.getName() + " and " + appliedCoupon.getName() + " are mutually exclusive.");
                }
            }

            couponGroupsApplied.add(coupon.getCouponGroup());
            couponsToApply.add(coupon);
        }

        couponsToApply.sort(Comparator.comparingInt(c->c.getDiscountPhase().getWeight()));

        cart.resetToOriginalTotal();

        for(Coupon c: couponsToApply){
            double discount=c.getDiscount(cart);
            cart.applyDiscount(discount);
            System.out.println("Applied [" + c.getDiscountPhase() + "] " + c.getName() + " -> Discount: Rs " + discount);
        }

        return cart.getCurrentTotal();
    }

}

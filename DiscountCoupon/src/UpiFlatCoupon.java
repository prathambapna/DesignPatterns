public class UpiFlatCoupon extends Coupon{
    private final String upiDomain;
    private final DiscountStrategy discountStrategy;
    private final double flatOff;
    private final String id;
    private final double threshold;

    public UpiFlatCoupon(String id,String upiDomain, double flatOff,double threshold) {
        this.upiDomain = upiDomain;
        this.discountStrategy = DiscountStrategyFactory.getStrategy(StrategyType.FLAT,flatOff,0.0);
        this.flatOff = flatOff;
        this.id = id;
        this.threshold=threshold;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Flat Rs "+flatOff +" off on "+upiDomain;
    }

    @Override
    public CouponGroup getCouponGroup() {
        return CouponGroup.PAYMENT_OFFER;
    }

    @Override
    public DiscountPhase getDiscountPhase() {
        return DiscountPhase.PAYMENT_OFFER;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return (cart.getUpiId()!=null && cart.getUpiId().endsWith(upiDomain) && cart.getCurrentTotal()>=threshold);
    }

    @Override
    public double getDiscount(Cart cart) {
        return discountStrategy.calculateDiscount(cart.getCurrentTotal());
    }
}

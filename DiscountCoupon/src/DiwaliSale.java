public class DiwaliSale extends Coupon{
    private final DiscountStrategy discountStrategy;
    private final double percentageOff;
    private final String id;
    private final double threshold;
    private final double cap;

    public DiwaliSale(String id,double percentageOff, double threshold,double cap) {
        this.percentageOff = percentageOff;
        this.id = id;
        this.threshold = threshold;
        this.cap=cap;
        this.discountStrategy=DiscountStrategyFactory.getStrategy(StrategyType.PERCENTAGE_WITH_CAP,cap,percentageOff);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Diwali sale: "+percentageOff+"% off upto "+cap;
    }

    @Override
    public CouponGroup getCouponGroup() {
        return CouponGroup.STORE_PROMO;
    }

    @Override
    public DiscountPhase getDiscountPhase() {
        return DiscountPhase.CART_PERCENTAGE;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getOriginalTotal()>threshold;
    }

    @Override
    public double getDiscount(Cart cart) {
        return discountStrategy.calculateDiscount(cart.getCurrentTotal());
    }
}

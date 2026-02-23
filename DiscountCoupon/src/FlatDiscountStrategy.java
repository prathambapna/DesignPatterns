public class FlatDiscountStrategy implements DiscountStrategy{
    private final double flatAmount;

    public FlatDiscountStrategy(double flatAmount){
        this.flatAmount=flatAmount;
    }

    @Override
    public double calculateDiscount(double amount) {
        return Math.min(flatAmount,amount);
    }
}

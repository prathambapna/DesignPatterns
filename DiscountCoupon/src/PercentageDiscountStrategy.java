public class PercentageDiscountStrategy implements DiscountStrategy{

    private final double percentage;

    public PercentageDiscountStrategy(double percentage){
        this.percentage=percentage;
    }
    @Override
    public double calculateDiscount(double amount) {
        return (percentage/100.0)*amount;
    }
}

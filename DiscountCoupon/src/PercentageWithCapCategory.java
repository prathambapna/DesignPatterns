public class PercentageWithCapCategory implements DiscountStrategy{
    private final double cap;
    private final double percentage;

    public PercentageWithCapCategory(double cap,double percentage){
        this.percentage=percentage;
        this.cap=cap;
    }
    @Override
    public double calculateDiscount(double amount) {
        double percentageDisc= (percentage/100.0)*amount;
        return Math.min(percentageDisc,cap);
    }
}

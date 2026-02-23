public class DiscountStrategyFactory {
    public static DiscountStrategy getStrategy(StrategyType strategyType, double param1, double param2){
        switch (strategyType){
            case FLAT : return new FlatDiscountStrategy(param1);
            case PERCENTAGE: return new PercentageDiscountStrategy(param1);
            case PERCENTAGE_WITH_CAP: return  new PercentageWithCapCategory(param1,param2);
            default:throw new IllegalArgumentException("Unknown Strategy");
        }
    }
}

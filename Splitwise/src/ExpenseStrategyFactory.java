public class ExpenseStrategyFactory {
    public static ExpenseStrategy getExpenseStrategy(ExpenseStrategyType type){
        return switch (type) {
            case EQUAL -> new EqualExpenseStrategy();
            case PERCENTAGE -> new PercentageExpenseStrategy();
            case UNEQUAL -> new UnequalExpenseStrategy();
            default -> new EqualExpenseStrategy();
        };
    }
}

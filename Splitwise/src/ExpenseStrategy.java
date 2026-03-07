import java.math.BigDecimal;
import java.util.List;

public interface ExpenseStrategy {
    public List<Participant> calculateExpense(BigDecimal amount, List<String>userIds,List<BigDecimal>splits);
}

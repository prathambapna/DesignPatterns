import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PercentageExpenseStrategy implements ExpenseStrategy{
    @Override
    public List<Participant> calculateExpense(BigDecimal amount, List<String> userIds, List<BigDecimal> splits) {
        int totalUsers=userIds.size();
        List<Participant>splitList=new ArrayList<>();

        for(int i=0;i<totalUsers;i++){
            BigDecimal contro=amount.multiply(splits.get(i)).divide(BigDecimal.valueOf(100),2, RoundingMode.CEILING);
            splitList.add(new Participant(userIds.get(i),contro));
        }

        return splitList;
    }
}

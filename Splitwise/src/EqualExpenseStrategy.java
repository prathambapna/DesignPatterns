import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class EqualExpenseStrategy implements ExpenseStrategy{
    @Override
    public List<Participant> calculateExpense(BigDecimal amount, List<String> userIds, List<BigDecimal> splits) {
        List<Participant> splitList=new ArrayList<>();
        BigDecimal noOfUsers= BigDecimal.valueOf(userIds.size());
        BigDecimal individualAmount=amount.divide(noOfUsers,2, RoundingMode.CEILING);

        for(String id:userIds){
            splitList.add(new Participant(id,individualAmount));
        }
        return splitList;
    }
}

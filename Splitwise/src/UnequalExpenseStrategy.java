import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UnequalExpenseStrategy implements ExpenseStrategy{
    @Override
    public List<Participant> calculateExpense(BigDecimal amount, List<String> userIds, List<BigDecimal> splits) {
        int totalUsers=userIds.size();
        List<Participant>splitList=new ArrayList<>();

        for(int i=0;i<totalUsers;i++){
            splitList.add(new Participant(userIds.get(i),splits.get(i)));
        }

        return splitList;
    }
}

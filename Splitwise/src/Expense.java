import java.math.BigDecimal;
import java.util.List;

public class Expense {
    private static int id=0;
    private String expenseId;
    public String desc;
    public BigDecimal amount;
    List<Participant>participantsWithShare;
    public String payerId;
    public String groupId;

    public Expense (String desc, BigDecimal amount, List<Participant>participantsWithShare,String payerId, String groupId){
        this.expenseId="Expense_"+(++id);
        this.amount=amount;
        this.desc=desc;
        this.groupId=groupId;
        this.payerId=payerId;
        this.participantsWithShare=participantsWithShare;
    }

    public Expense(String desc, BigDecimal amount, String paidBy, List<Participant> splits) {
        this(desc, amount,splits, paidBy, "");
    }


}

import java.math.BigDecimal;

public class Participant {
    public String userId;
    public BigDecimal amount;
    public Participant(String uid,BigDecimal amount){
        this.amount=amount;
        this.userId=uid;
    }
}

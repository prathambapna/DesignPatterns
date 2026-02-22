public class SubscriptionRequest extends PaymentRequest{
    String frequency;
    String billingCycle;

    public SubscriptionRequest(String requester,String sender,double amount,String currency,String frequency,String billingCycle){
        super(requester,sender,amount,currency);
        this.billingCycle=billingCycle;
        this.frequency=frequency;
    }
}

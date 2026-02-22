public class PaymentRequest {
    String requester;
    String sender;
    double amount;
    String currency;

    public PaymentRequest(String requester,String sender,double amount,String currency){
        this.amount=amount;
        this.requester=requester;
        this.sender=sender;
        this.currency=currency;
    }
}

public abstract class PaymentGateway {
    protected abstract boolean validatePayment(PaymentRequest pr);
    protected abstract void confirmPayment(PaymentRequest pr);
    protected abstract void initiatePayment(PaymentRequest pr);

    protected abstract boolean validateSubscription(SubscriptionRequest sr);
    protected abstract String createMandate(SubscriptionRequest sr);
    protected abstract void confirmSubscription(SubscriptionRequest sr,String mandateId);

    public  boolean processPayment(PaymentRequest pr) {
        System.out.println("Starting standard payment flow...");

        if (validatePayment(pr)) {
            try {
                initiatePayment(pr);
                confirmPayment(pr);
                System.out.println("Payment successful.");
                return true;
            } catch (Exception e) {
                System.out.println("Payment failed during processing: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Payment validation failed. Aborting.");
            return false;
        }
    }

    public  boolean setupSubscription(SubscriptionRequest sr) {
        System.out.println("Starting recurring payment flow...");

        if (validateSubscription(sr)) {
            try {
                // Instead of a direct charge, we create a mandate/token
                String mandateId = createMandate(sr);
                confirmSubscription(sr, mandateId);
                System.out.println("Subscription active! Mandate ID: " + mandateId);
                return true;
            } catch (Exception e) {
                System.out.println("Subscription failed: " + e.getMessage());
                return false;
            }
        }
        return false;
    }
}

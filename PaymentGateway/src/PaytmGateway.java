public class PaytmGateway extends PaymentGateway {

    // The Adapter holds an instance of the Adaptee
    private final PaytmThirdPartySDK paytmSDK;

    public PaytmGateway() {
        // In a real Spring Boot app, this might be injected.
        // For simplicity here, we instantiate the SDK directly.
        this.paytmSDK = new PaytmThirdPartySDK();
    }

    // ==========================================
    // ONE-TIME PAYMENT FLOW IMPLEMENTATIONS
    // ==========================================

    @Override
    protected boolean validatePayment(PaymentRequest pr) {
        System.out.println("Paytm Adapter: Validating one-time request for sender: " + pr.sender);
        // Custom Paytm validation logic (e.g., checking if currency is INR)
        if (!"INR".equalsIgnoreCase(pr.currency)) {
            System.out.println("Paytm Adapter: Validation failed. Paytm only supports INR.");
            return false;
        }
        return true;
    }

    @Override
    protected void initiatePayment(PaymentRequest pr) {
        System.out.println("Paytm Adapter: Initiating transaction...");

        // --- ADAPTER MAGIC HAPPENS HERE ---
        // We translate our 'PaymentRequest' into the specific parameters Paytm needs
        String transactionId = paytmSDK.sendMoney(pr.amount, pr.sender);

        System.out.println("Paytm Adapter: Received Txn ID from SDK: " + transactionId);
    }

    @Override
    protected void confirmPayment(PaymentRequest pr) {
        System.out.println("Paytm Adapter: Confirming transaction with Paytm servers...");
        // Logic to verify the transaction status
    }

    // ==========================================
    // SUBSCRIPTION FLOW IMPLEMENTATIONS
    // ==========================================

    @Override
    protected boolean validateSubscription(SubscriptionRequest sr) {
        System.out.println("Paytm Adapter: Validating " + sr.frequency + " subscription request.");
        // Paytm might have strict rules, like amounts > ₹100 for subscriptions
        if (sr.amount < 100) {
            System.out.println("Paytm Adapter: Subscription amount must be at least ₹100.");
            return false;
        }
        return true;
    }

    @Override
    protected String createMandate(SubscriptionRequest sr) {
        System.out.println("Paytm Adapter: Creating mandate...");

        // --- ADAPTER MAGIC HAPPENS HERE ---
        // Translating our 'SubscriptionRequest' to Paytm's auto-debit setup
        String mandateId = paytmSDK.setupAutoDebit(sr.amount, sr.frequency);

        return mandateId;
    }

    @Override
    protected void confirmSubscription(SubscriptionRequest sr, String mandateId) {
        System.out.println("Paytm Adapter: Activating mandate [" + mandateId + "] for user " + sr.sender);
        // Logic to finalize the auto-debit registration
    }
}
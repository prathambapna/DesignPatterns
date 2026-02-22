public class RazorpayGatewayAdapter extends PaymentGateway {

    // The Adapter holds an instance of the Adaptee
    private final RazorpayThirdPartySDK razorpaySDK;

    public RazorpayGatewayAdapter() {
        // Instantiating the 3rd-party SDK
        this.razorpaySDK = new RazorpayThirdPartySDK();
    }

    // ==========================================
    // ONE-TIME PAYMENT FLOW IMPLEMENTATIONS
    // ==========================================

    @Override
    protected boolean validatePayment(PaymentRequest pr) {
        System.out.println("Razorpay Adapter: Validating one-time request for sender: " + pr.requester);
        // Razorpay supports international currencies, so we might just validate the amount here
        if (pr.amount <= 0) {
            System.out.println("Razorpay Adapter: Validation failed. Amount must be greater than 0.");
            return false;
        }
        return true;
    }

    @Override
    protected void initiatePayment(PaymentRequest pr) {
        System.out.println("Razorpay Adapter: Initiating transaction...");

        // --- ADAPTER MAGIC HAPPENS HERE ---
        // Translating our standard 'PaymentRequest' to Razorpay's specific parameters
        String paymentId = razorpaySDK.chargeCard(pr.amount, pr.currency, pr.requester);

        System.out.println("Razorpay Adapter: Received Payment ID from SDK: " + paymentId);
    }

    @Override
    protected void confirmPayment(PaymentRequest pr) {
        System.out.println("Razorpay Adapter: Verifying payment signature with Razorpay servers...");
        // Logic to verify the webhook signature or transaction status
    }

    // ==========================================
    // SUBSCRIPTION FLOW IMPLEMENTATIONS
    // ==========================================

    @Override
    protected boolean validateSubscription(SubscriptionRequest sr) {
        System.out.println("Razorpay Adapter: Validating " + sr.frequency + " subscription.");
        // Razorpay might require specific frequency strings like "monthly" or "yearly"
        if (sr.frequency == null || sr.frequency.isEmpty()) {
            System.out.println("Razorpay Adapter: Frequency must be specified for Razorpay subscriptions.");
            return false;
        }
        return true;
    }

    @Override
    protected String createMandate(SubscriptionRequest sr) {
        System.out.println("Razorpay Adapter: Creating subscription mandate...");

        // --- ADAPTER MAGIC HAPPENS HERE ---
        // Translating our 'SubscriptionRequest' to Razorpay's recurring token setup
        String subscriptionId = razorpaySDK.registerRecurringToken(sr.frequency, sr.requester);

        return subscriptionId;
    }

    @Override
    protected void confirmSubscription(SubscriptionRequest sr, String mandateId) {
        System.out.println("Razorpay Adapter: Activating subscription [" + mandateId + "] for user " + sr.requester);
        // Logic to capture the initial payment and activate the subscription
    }
}
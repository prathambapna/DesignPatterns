public class PaymentGatewayProxy extends PaymentGateway {

    // The Proxy wraps ANY implementation of a PaymentGateway
    private final PaymentGateway realGateway;
    private final int maxRetries;
    private final RetryStrategy retryStrategy;

    // Constructor Injection for the dependencies
    public PaymentGatewayProxy(PaymentGateway realGateway, int maxRetries, RetryStrategy retryStrategy) {
        this.realGateway = realGateway;
        this.maxRetries = maxRetries;
        this.retryStrategy = retryStrategy;
    }

    @Override
    protected boolean validatePayment(PaymentRequest pr) {
        return realGateway.validatePayment(pr);
    }

    @Override
    protected void confirmPayment(PaymentRequest pr) {
        realGateway.confirmPayment(pr);
    }

    @Override
    protected void initiatePayment(PaymentRequest pr) {
        realGateway.initiatePayment(pr);
    }

    @Override
    protected boolean validateSubscription(SubscriptionRequest sr) {
        return realGateway.validateSubscription(sr);
    }

    @Override
    protected String createMandate(SubscriptionRequest sr) {
        return realGateway.createMandate(sr);
    }

    @Override
    protected void confirmSubscription(SubscriptionRequest sr, String mandateId) {
        realGateway.confirmSubscription(sr, mandateId);
    }

    // 1. Wrapping the One-Time Payment Flow
    @Override
    public boolean processPayment(PaymentRequest request) {
        boolean result = false;

        System.out.println("\n[Proxy] Starting one-time payment for " + request.requester);

        for (int attempt = 0; attempt < maxRetries; attempt++) {
            if (attempt > 0) {
                System.out.println("[Proxy] Retrying payment (attempt " + (attempt + 1) + ")...");
                retryStrategy.wait(attempt); // The Strategy handles the delay logic!
            }

            // Delegate the actual work to the real gateway (which triggers the Template Method)
            result = realGateway.processPayment(request);

            if (result) {
                break; // Success! Exit the retry loop.
            }
        }

        if (!result) {
            System.out.println("[Proxy] Payment failed permanently after " + maxRetries + " attempts.");
        }

        return result;
    }

    // 2. Wrapping the Subscription Flow
    @Override
    public boolean setupSubscription(SubscriptionRequest request) {
        boolean result = false;

        System.out.println("\n[Proxy] Starting subscription setup for " + request.requester);

        for (int attempt = 0; attempt < maxRetries; attempt++) {
            if (attempt > 0) {
                System.out.println("[Proxy] Retrying subscription setup (attempt " + (attempt + 1) + ")...");
                retryStrategy.wait(attempt);
            }

            // Delegate to the real gateway
            result = realGateway.setupSubscription(request);

            if (result) {
                break; // Success! Exit the retry loop.
            }
        }

        if (!result) {
            System.out.println("[Proxy] Subscription setup failed permanently after " + maxRetries + " attempts.");
        }

        return result;
    }
}
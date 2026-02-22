public class GatewayFactory {

    /**
     * Creates and configures the requested Payment Gateway.
     * It builds the Adapter, pairs it with a Strategy, and wraps them in a Proxy.
     */
    public PaymentGateway getGateway(GatewayType type) {

        switch (type) {
            case PAYTM:
                // 1. Create the base Adapter that talks to the 3rd Party SDK
                PaymentGateway paytmAdapter = new PaytmGateway() {
                };

                // 2. Choose the Retry Strategy (e.g., Linear retry every 2 seconds)
                RetryStrategy linearStrategy = new LinearRetry(2000);

                // 3. Wrap it all in the Proxy (e.g., allow up to 3 attempts total)
                System.out.println("[Factory] Assembling Paytm Gateway with Linear Retry Strategy...");
                return new PaymentGatewayProxy(paytmAdapter, 3, linearStrategy);

            case RAZORPAY:
                // 1. Create the base Adapter
                PaymentGateway razorpayAdapter = new RazorpayGatewayAdapter();

                // 2. Choose the Retry Strategy (e.g., Exponential back-off starting at 1 second)
                RetryStrategy exponentialStrategy = new ExponentialBackOffRetry(1000);

                // 3. Wrap it in the Proxy (e.g., allow up to 4 attempts total)
                System.out.println("[Factory] Assembling Razorpay Gateway with Exponential Retry Strategy...");
                return new PaymentGatewayProxy(razorpayAdapter, 4, exponentialStrategy);

            default:
                // Always fail fast if an unknown gateway is requested
                throw new IllegalArgumentException("Unsupported Gateway Type: " + type);
        }
    }
}
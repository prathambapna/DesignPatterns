public class PaymentController {

    // The Service is injected here.
    private final PaymentService paymentService;

    // Constructor Injection
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Endpoint for: POST /api/payments
     */
    public void handlePayment(GatewayType type, PaymentRequest request) {
        System.out.println("\n--- [API POST] Received One-Time Payment Request ---");
        try {
            boolean isSuccess = paymentService.processPayment(type, request);

            if (isSuccess) {
                System.out.println("Controller: Payment completed successfully. Returning HTTP 200 OK.");
            } else {
                System.out.println("Controller: Payment failed. Returning HTTP 400 Bad Request.");
            }
        } catch (Exception e) {
            System.err.println("Controller: System Error - " + e.getMessage() + ". Returning HTTP 500.");
        }
    }

    /**
     * Endpoint for: POST /api/subscriptions
     */
    public void handleSubscription(GatewayType type, SubscriptionRequest request) {
        System.out.println("\n--- [API POST] Received Subscription Request ---");
        try {
            boolean isSuccess = paymentService.startSubscription(type, request);

            if (isSuccess) {
                System.out.println("Controller: Subscription active. Returning HTTP 200 OK.");
            } else {
                System.out.println("Controller: Subscription failed. Returning HTTP 400 Bad Request.");
            }
        } catch (Exception e) {
            System.err.println("Controller: System Error - " + e.getMessage() + ". Returning HTTP 500.");
        }
    }
}
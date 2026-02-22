public class Main {
    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("   1. BOOTSTRAPPING THE APPLICATION");
        System.out.println("==========================================");

        // In a real Spring Boot app, the framework handles this wiring automatically.
        // Here, we manually inject the dependencies via constructors.
        GatewayFactory gatewayFactory = new GatewayFactory();
        PaymentService paymentService = new PaymentService(gatewayFactory);
        PaymentController paymentController = new PaymentController(paymentService);

        System.out.println("Application wired successfully. Ready for requests.\n");


        System.out.println("==========================================");
        System.out.println("   2. TESTING ONE-TIME PAYMENT (PAYTM)");
        System.out.println("==========================================");

        // Using your exact PaymentRequest constructor:
        // (requester, sender, amount, currency)
        PaymentRequest oneTimeReq = new PaymentRequest(
                "Amazon_India",
                "User_John",
                1500.0,
                "INR"
        );

        // Send the request to the controller
        paymentController.handlePayment(GatewayType.PAYTM, oneTimeReq);


        System.out.println("\n==========================================");
        System.out.println("   3. TESTING SUBSCRIPTION (RAZORPAY)");
        System.out.println("==========================================");

        // Using your exact SubscriptionRequest constructor:
        // (requester, sender, amount, currency, frequency, billingCycle)
        SubscriptionRequest subReq = new SubscriptionRequest(
                "Netflix",
                "User_Jane",
                199.0,
                "INR",
                "MONTHLY",
                "5th of every month"
        );

        // Send the subscription request to the controller
        paymentController.handleSubscription(GatewayType.RAZORPAY, subReq);


        System.out.println("\n==========================================");
        System.out.println("   SYSTEM SHUTDOWN");
        System.out.println("==========================================");
    }
}
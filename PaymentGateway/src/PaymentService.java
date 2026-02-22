public class PaymentService {

    // The Factory is injected here. The Service doesn't know about Adapters or Proxies!
    private final GatewayFactory gatewayFactory;

    // Constructor Injection
    public PaymentService(GatewayFactory gatewayFactory) {
        this.gatewayFactory = gatewayFactory;
    }

    /**
     * Handles standard, one-time payments.
     */
    public boolean processPayment(GatewayType type, PaymentRequest request) {
        System.out.println("PaymentService: Routing one-time payment via " + type);

        // 1. Get the fully assembled gateway (Adapter + Strategy + Proxy)
        PaymentGateway gateway = gatewayFactory.getGateway(type);

        // 2. Execute the flow
        return gateway.processPayment(request);
    }

    /**
     * Handles recurring subscription setups.
     */
    public boolean startSubscription(GatewayType type, SubscriptionRequest request) {
        System.out.println("PaymentService: Routing subscription setup via " + type);

        // 1. Get the fully assembled gateway
        PaymentGateway gateway = gatewayFactory.getGateway(type);

        // 2. Execute the subscription flow
        return gateway.setupSubscription(request);
    }
}
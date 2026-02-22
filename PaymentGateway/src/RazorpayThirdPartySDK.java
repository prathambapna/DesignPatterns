public class RazorpayThirdPartySDK {

    // Razorpay's specific method for one-time transactions
    public String chargeCard(double amount, String currencyCode, String customerId) {
        System.out.println("   [Razorpay SDK] Charging " + currencyCode + " " + amount + " for customer " + customerId);
        return "pay_" + System.currentTimeMillis(); // Razorpay typically uses 'pay_' prefixes
    }

    // Razorpay's specific method for subscriptions/recurring payments
    public String registerRecurringToken(String planType, String customerId) {
        System.out.println("   [Razorpay SDK] Registering " + planType + " plan for customer " + customerId);
        return "sub_" + System.currentTimeMillis(); // Razorpay typically uses 'sub_' prefixes
    }
}
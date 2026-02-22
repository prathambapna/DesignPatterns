public class PaytmThirdPartySDK {

    // Paytm's specific method for one-time transactions
    public String sendMoney(double amount, String payeeId) {
        System.out.println("   [Paytm SDK] Processing transfer of ₹" + amount + " to " + payeeId);
        return "PTM_TXN_" + System.currentTimeMillis();
    }

    // Paytm's specific method for subscriptions/recurring payments
    public String setupAutoDebit(double amount, String frequencyStr) {
        System.out.println("   [Paytm SDK] Registering " + frequencyStr + " auto-debit for ₹" + amount);
        return "PTM_MANDATE_" + System.currentTimeMillis();
    }
}
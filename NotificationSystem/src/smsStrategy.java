public class smsStrategy implements NotificationStrategy{
    private final String phoneNumber;
    public smsStrategy(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    @Override
    public void sendNotification(String notificationContent) {
        System.out.println("Sending sms to: " + phoneNumber+ ", Content: "+notificationContent);
    }
}

public class EmailStrategy implements NotificationStrategy{
    private final String email;
    public EmailStrategy(String email){
        this.email=email;
    }
    public void sendNotification(String notificationContent){
        System.out.println("Sending email to: " + email+ ", Content: "+notificationContent);
    }
}

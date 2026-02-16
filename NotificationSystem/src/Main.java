//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Notification notification=new SimpleNotification("Hello, How are you?");
//        System.out.println(notification.getContent());
        notification=new TimeStampDecorator(notification);
//        System.out.println(notification.getContent());
        notification=new SignatureDecorator(notification,"Notification company sends you following notification");
//        System.out.println(notification.getContent());

        NotificationService notificationService=NotificationService.getInstance();

        NotificationEngine notificationEngine=new NotificationEngine();
        EmailStrategy emailStrategy=new EmailStrategy("pratham@gmail.com");
        smsStrategy smsStrategy=new smsStrategy("9876543211");
        notificationEngine.addStrategy(emailStrategy);
        notificationEngine.addStrategy(smsStrategy);

        Logger logger=new Logger();
        notificationService.sendNotification(notification);

        Notification n2=new SimpleNotification("Hi, user");
        n2=new TimeStampDecorator(n2);

        notificationEngine.removeStrategy(emailStrategy);

        notificationService.sendNotification(n2);
    }
}
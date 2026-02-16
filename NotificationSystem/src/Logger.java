public class Logger implements Observer{
    private final NotificationObservable notificationObservable;
    public Logger(){
        this.notificationObservable= NotificationService.getInstance().getNotificationObservable();
        notificationObservable.add(this);
    }
//    public Logger(NotificationObservable notificationObservable){
//        this.notificationObservable=notificationObservable;
//    }

    @Override
    public void update() {
        System.out.println("Logs: " + notificationObservable.getNotificationContent());
    }
}

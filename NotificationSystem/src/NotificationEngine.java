import java.util.ArrayList;
import java.util.List;

public class NotificationEngine implements Observer{
    private final List<NotificationStrategy>notificationStrategies=new ArrayList<>();
    private final NotificationObservable notificationObservable;

    public NotificationEngine(){
        this.notificationObservable= NotificationService.getInstance().getNotificationObservable();
        notificationObservable.add(this);
    }

//    public NotificationEngine(NotificationObservable notificationObservable){
//        this.notificationObservable=notificationObservable;
//    }

    public void addStrategy(NotificationStrategy notificationStrategy){
        notificationStrategies.add(notificationStrategy);
    }

    public void removeStrategy(NotificationStrategy notificationStrategy){
        notificationStrategies.remove(notificationStrategies.contains(notificationStrategy)?notificationStrategy:null);
    }

    public void update(){
        for(NotificationStrategy ns: notificationStrategies){
            ns.sendNotification(notificationObservable.getNotificationContent());
        }
    }

}

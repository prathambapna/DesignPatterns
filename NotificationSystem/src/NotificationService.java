import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private static volatile NotificationService instance;
    private final List<Notification>notificationList=new ArrayList<>();
    private final NotificationObservable notificationObservable;

    private NotificationService(){
        notificationObservable=new NotificationObservable();
        if(instance!=null){
            throw new RuntimeException("Don't try to be smart");
        }
    }

    public static NotificationService getInstance(){
        if(instance==null){
            synchronized (NotificationService.class){
                if(instance==null){
                    instance=new NotificationService();
                }
            }
        }
        return instance;
    }

    public void sendNotification(Notification notification){
        notificationObservable.setNotification(notification);
    }

    public NotificationObservable getNotificationObservable(){
        return this.notificationObservable;
    }
}

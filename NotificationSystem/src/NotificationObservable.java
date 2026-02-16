import java.util.ArrayList;
import java.util.List;

public class NotificationObservable implements Observable{
    final private List<Observer>observerList=new ArrayList<>();
    private Notification notification;

    public void add(Observer observer){
        observerList.add(observer);
    }

    public void remove(Observer observer){
        observerList.remove(observerList.contains(observer)?observer:null);
    }

    public void notifyUser(){
        for(Observer ob:observerList){
            ob.update();
        }
    }

    public void setNotification(Notification notification){
        this.notification=notification;
        notifyUser();
    }

    public String getNotificationContent(){
        return this.notification.getContent();
    }

    public Notification getNotification(){
        return this.notification;
    }
}

public abstract class NotificationDecorator implements Notification{
    protected Notification notification;
    NotificationDecorator(Notification notification){
        this.notification=notification;
    }
    public String getContent(){
        return notification.getContent();
    }
}

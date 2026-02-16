import java.time.LocalDateTime;

public class TimeStampDecorator extends NotificationDecorator{
    final private LocalDateTime myObj = LocalDateTime.now();
    public TimeStampDecorator(Notification notification){
        super(notification);
    }

    @Override
    public String getContent() {
        return super.getContent() + " , Time: " + myObj.toString();
    }
}

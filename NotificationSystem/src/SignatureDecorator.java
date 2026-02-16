public class SignatureDecorator extends NotificationDecorator{
    private String signature;
    public SignatureDecorator(Notification notification, String signature){
        super(notification);
        this.signature=signature;
    }
    public String getContent(){
        return "[Signature: "+ signature+ "]:"+super.getContent();
    }
}

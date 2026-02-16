public class SimpleNotification implements Notification{
    final private String text;
    public SimpleNotification(String text){
        this.text=text;
    }
    @Override
    public String getContent(){
        return text;
    }
}

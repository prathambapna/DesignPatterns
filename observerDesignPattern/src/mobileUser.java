public class mobileUser implements Observer{
    private String user;

    public mobileUser(String user){
        this.user=user;
    }
    @Override
    public void update(String news){
        System.out.println(user + " received alert " + news);
    }
}

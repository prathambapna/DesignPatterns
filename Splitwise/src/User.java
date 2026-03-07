public class User implements Observer {
    String name;
    private String userId;
    static int id=0;
    public void update(String message){
        System.out.println("Updated "+name +" with :" +message);
    }

    public User(String name){
        this.name=name;
        this.userId="user_"+(++id);
    }

    public String getUserId() {
        return userId;
    }
}

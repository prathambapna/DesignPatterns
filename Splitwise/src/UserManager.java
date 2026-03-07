import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String,User>users=new HashMap<>();

    private static UserManager instance;


    public static UserManager getInstance() {
        if(instance == null) {
            instance = new UserManager();

        }
        return instance;

    }
    public User addUser(String name){
        User user=new User(name);
        users.put(user.getUserId(),user);
        System.out.println("User created: " + name + " (ID: " + user.getUserId() + ")");
        return user;
    }

    public User getUser(String userId){
        return users.getOrDefault(userId,null);
    }
}

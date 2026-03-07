import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupManager {
    private Map<String,Group>groups=new HashMap<>();
    private UserManager userManager=UserManager.getInstance();


    private static GroupManager instance;


    public static GroupManager getInstance() {
        if(instance == null) {
            instance = new GroupManager();

        }
        return instance;

    }
    public Group addGroup(String name){
        Group g=new Group(name);
        this.groups.put(g.getGroupId(),g);
        System.out.println("Group created: " + name + " (ID: " + g.getGroupId() + ")");
        return g;
    }

    public Group getGroup(String groupId){
        return groups.getOrDefault(groupId,null);
    }

    public void addUserToGroup(String userId, String groupId) {
        User user = userManager.getUser(userId);
        Group group = getGroup(groupId);

        if (user != null && group != null) {
            group.addMember(user);
        }
    }

    public boolean removeUserFromGroup(String userId, String groupId) {
        Group group = getGroup(groupId);

        if (group == null) {
            System.out.println("Group not found!");
            return false;
        }

        User user = userManager.getUser(userId);
        if (user == null) {
            System.out.println("User not found!");
            return false;
        }

        boolean userRemoved = group.removeMember(userId);

        if(userRemoved) {
            System.out.println(user.name + " successfully left " + group.getName());
        }
        return userRemoved;
    }

    public void addExpenseToGroup(String groupId, String description, BigDecimal amount,
                                  String paidByUserId, List<String> involvedUsers,
                                  ExpenseStrategyType splitType, List<BigDecimal> splitValues) {

        Group group = getGroup(groupId);
        if (group == null) {
            System.out.println("Group not found!");
            return;
        }

        group.addExpense(description, amount, paidByUserId, involvedUsers, splitType, splitValues);
    }

    // Settlement - delegate to group
    public void settlePaymentInGroup(String groupId, String fromUserId,
                                     String toUserId, BigDecimal amount) {

        Group group = getGroup(groupId);
        if (group == null) {
            System.out.println("Group not found!");
            return;
        }

        group.settlePayment(fromUserId, toUserId, amount);
    }

    public void showGroupBalances(String groupId) {
        Group group = getGroup(groupId);
        if (group == null) return;

        group.showGroupBalances();
    }

}

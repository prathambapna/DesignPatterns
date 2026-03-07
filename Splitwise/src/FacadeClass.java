import java.math.BigDecimal;
import java.util.List;

public class FacadeClass {
    private UserManager userManager=UserManager.getInstance();
    private GroupManager groupManager=GroupManager.getInstance();

    private static FacadeClass instance;


    public static FacadeClass getInstance() {
        if(instance == null) {
            instance = new FacadeClass();

        }
        return instance;

    }
    // User management
    public User createUser(String name) {
        User user = userManager.addUser(name);
        return user;
    }

    public User getUser(String userId) {
        return userManager.getUser(userId);
    }

    // Group management
    public Group createGroup(String name) {
        Group group = groupManager.addGroup(name);
        return group;
    }

    public Group getGroup(String groupId) {
        return groupManager.getGroup(groupId);
    }

    public void addUserToGroup(String userId, String groupId) {
        groupManager.addUserToGroup(userId,groupId);
    }


    // Try to remove user from group - just delegates to group
    public boolean removeUserFromGroup(String userId, String groupId) {
        return groupManager.removeUserFromGroup(userId,groupId);
    }

    // Expense management - delegate to group
    public void addExpenseToGroup(String groupId, String description, BigDecimal amount,
                                  String paidByUserId, List<String> involvedUsers,
                                  ExpenseStrategyType splitType, List<BigDecimal> splitValues) {

        groupManager.addExpenseToGroup(groupId,description,amount,paidByUserId,involvedUsers,splitType,splitValues);
    }

    public void settlePaymentInGroup(String groupId, String fromUserId,
                                     String toUserId, BigDecimal amount) {
        groupManager.settlePaymentInGroup(groupId,fromUserId,toUserId,amount);
    }

    public void showGroupBalances(String groupId) {
        groupManager.showGroupBalances(groupId);
    }
}

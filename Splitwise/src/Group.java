import java.math.BigDecimal;
import java.util.*;

public class Group {
    private static int id=0;
    private String groupId;
    private String name;
    private List<User>users;
    private List<Expense>expenses;
    private Map<String, Map<String, BigDecimal>> balances;

    public Group(String name){
        this.name=name;
        this.groupId="group_"+(++id);
        users=new ArrayList<>();
        expenses=new ArrayList<>();
        balances=new HashMap<>();
    }

    private User getUserByuserId(String userId) {
        User user = null;

        for(User member : users) {
            if(member.getUserId().equals(userId)) {
                user = member;
            }
        }
        return user;
    }


    public void addMember(User user) {
        users.add(user);

        // Initialize balance map for new member
        balances.put(user.getUserId(), new HashMap<>());
        System.out.println(user.name + " added to group " + name);
    }

    public boolean removeMember(String userId) {
        // Check if user can be removed or not
        if(!canUserLeaveGroup(userId)) {
            System.out.println("\nUser not allowed to leave group without clearing expenses");
            return false;
        }

        // Remove from observers
        users.removeIf(user -> user.getUserId().equals(userId));

        // Remove from group balances
        balances.remove(userId);

        // Remove this user from other members' balance maps
        for (Map.Entry<String, Map<String, BigDecimal>> memberBalance : balances.entrySet()) {
            memberBalance.getValue().remove(userId);
        }
        return true;
    }
    public void notifyMembers(String message) {
        for (Observer observer : users) {
            observer.update(message);
        }
    }
    public boolean canUserLeaveGroup(String userId) {
        if (!isMember(userId)) {
            throw new RuntimeException("user is not a part of this group");
        }

        // Check if user has any outstanding balance with other group members
        Map<String, BigDecimal> userBalanceSheet = balances.get(userId);
        for (Map.Entry<String, BigDecimal> balance : userBalanceSheet.entrySet()) {
            if (balance.getValue().abs().compareTo(BigDecimal.valueOf(0.01)) > 0) {
                return false; // Has outstanding balance
            }
        }
        return true;
    }

    // Get user's balance within this group
    public Map<String, BigDecimal> getUserGroupBalances(String userId) {
        if (!isMember(userId)) {
            throw new RuntimeException("user is not a part of this group");
        }
        return balances.get(userId);
    }

    public boolean isMember(String userId) {
        return balances.containsKey(userId);
    }

    public boolean addExpense(String description, BigDecimal amount, String paidByUserId,
                              List<String> involvedUsers, ExpenseStrategyType splitType,
                              List<BigDecimal> splitValues) {

        if (!isMember(paidByUserId)) {
            throw new RuntimeException("user is not a part of this group");
        }

        // Validate that all involved users are group members
        for (String userId : involvedUsers) {
            if (!isMember(userId)) {
                throw new RuntimeException("involvedUsers are not a part of this group");
            }
        }

        // Generate splits using strategy pattern
        List<Participant> splits = ExpenseStrategyFactory.getExpenseStrategy(splitType)
                .calculateExpense(amount, involvedUsers, splitValues);

        // Create expense in group's own expense book
        Expense expense = new Expense(description, amount, splits,paidByUserId, groupId);
        expenses.add(expense);

        // Update group balances
        for (Participant split : splits) {
            if (!split.userId.equals(paidByUserId)) {
                // Person who paid gets positive balance, person who owes gets negative
                updateBalance(paidByUserId, split.userId, split.amount);
            }
        }

        System.out.println("\n=========== Sending Notifications ====================");
        String paidByName = getUserByuserId(paidByUserId).name;
        notifyMembers("New expense added: " + description + " (Rs " + amount + ")");

        // Printing console message-------
        System.out.println("\n=========== Expense Message ====================");
        System.out.println("Expense added to " + name + ": " + description + " (Rs " + amount
                + ") paid by " + paidByName +" and involved people are : ");
        if(splitValues!=null && !splitValues.isEmpty()) {
            for(int i=0; i<splitValues.size(); i++) {
                System.out.println(getUserByuserId(involvedUsers.get(i)).name + " : " + splitValues.get(i));
            }
        }
        else {
            for(String user : involvedUsers) {
                System.out.print(getUserByuserId(user).name + ", ");
            }
            System.out.println("\nWill be Paid Equally");
        }
        //-----------------------------------

        return true;
    }

    public boolean addExpense(String description, BigDecimal amount, String paidByUserId,
                              List<String> involvedUsers, ExpenseStrategyType splitType) {
        return addExpense(description, amount, paidByUserId, involvedUsers, splitType, new ArrayList<>());
    }

    public boolean settlePayment(String fromUserId, String toUserId, BigDecimal amount) {
        // Validate that both users are group members
        if (!isMember(fromUserId) || !isMember(toUserId)) {
            System.out.println("user is not a part of this group");
            return false;
        }

        // Update group balances
        updateBalance(fromUserId, toUserId, amount);

        // Get user names for display
        String fromName = getUserByuserId(fromUserId).name;
        String toName = getUserByuserId(toUserId).name;

        // Notify group members
        notifyMembers("Settlement: " + fromName + " paid " + toName + " Rs " + amount);

        System.out.println("Settlement in " + name + ": " + fromName + " settled Rs "
                + amount + " with " + toName);

        return true;
    }

    public void showGroupBalances() {
        System.out.println("\n=== Group Balances for " + name + " ===");

        for (Map.Entry<String, Map<String, BigDecimal>> pair : balances.entrySet()) {
            String memberId = pair.getKey();
            String memberName = getUserByuserId(memberId).name;

            System.out.println(memberName + "'s balances in group:");

            Map<String, BigDecimal> userBalances = pair.getValue();
            if (userBalances.isEmpty()) {
                System.out.println("  No outstanding balances");
            }
            else {
                for (Map.Entry<String, BigDecimal> userBalance : userBalances.entrySet()) {
                    String otherMemberUserId = userBalance.getKey();
                    String otherName = getUserByuserId(otherMemberUserId).name;

                    BigDecimal balance = userBalance.getValue();
                    if (balance.compareTo(BigDecimal.valueOf(0))>0){
                        System.out.println("  " + otherName + " owes: Rs " + balance);
                    } else {
                        System.out.println("  Owes " + otherName + ": Rs " + balance.abs());
                    }
                }
            }
        }
    }


    private void updateBalance(String fromUserId, String toUserId,BigDecimal amount){
        balances.get(fromUserId).put(toUserId,
                balances.get(fromUserId).getOrDefault(toUserId, BigDecimal.valueOf(0)).add(amount));
        balances.get(toUserId).put(fromUserId,
                balances.get(toUserId).getOrDefault(fromUserId, BigDecimal.valueOf(0)).subtract(amount));

        // Remove if balance becomes zero
        if (balances.get(fromUserId).get(toUserId).abs().compareTo(BigDecimal.valueOf(0.01))>0) {
            balances.get(fromUserId).remove(toUserId);
        }
        if (balances.get(toUserId).get(fromUserId).abs().compareTo(BigDecimal.valueOf(0.01))>0) {
            balances.get(toUserId).remove(fromUserId);
        }
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public Map<String, Map<String, BigDecimal>> getBalances() {
        return balances;
    }
}

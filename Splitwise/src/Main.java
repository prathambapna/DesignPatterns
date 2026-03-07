import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FacadeClass manager = FacadeClass.getInstance();

        System.out.println("\n=========== Creating Users ====================");
        User user1 = manager.createUser("Aditya");
        User user2 = manager.createUser("Rohit");
        User user3 = manager.createUser("Manish");
        User user4 = manager.createUser("Saurav");

        System.out.println("\n=========== Creating Group and Adding Members ====================");
        Group hostelGroup = manager.createGroup("Hostel Expenses");
        manager.addUserToGroup(user1.getUserId(), hostelGroup.getGroupId());
        manager.addUserToGroup(user2.getUserId(), hostelGroup.getGroupId());
        manager.addUserToGroup(user3.getUserId(), hostelGroup.getGroupId());
        manager.addUserToGroup(user4.getUserId(), hostelGroup.getGroupId());

        System.out.println("\n=========== Adding Expenses in group ====================");
        List<String> groupMembers = Arrays.asList(user1.getUserId(), user2.getUserId(), user3.getUserId(), user4.getUserId());
        manager.addExpenseToGroup(hostelGroup.getGroupId(), "Lunch", BigDecimal.valueOf(800.0), user1.getUserId(), groupMembers, ExpenseStrategyType.EQUAL,null);

        List<String> dinnerMembers = Arrays.asList(user1.getUserId(), user3.getUserId(), user4.getUserId());
        List<BigDecimal> dinnerAmounts = List.of(
                BigDecimal.valueOf(200.0),
                BigDecimal.valueOf(300.0),
                BigDecimal.valueOf(200.0)
        );
        manager.addExpenseToGroup(hostelGroup.getGroupId(), "Dinner", BigDecimal.valueOf(700.0), user3.getUserId(), dinnerMembers,
                ExpenseStrategyType.UNEQUAL, dinnerAmounts);

        System.out.println("\n=========== printing Group-Specific Balances ====================");
        manager.showGroupBalances(hostelGroup.getGroupId());

//        System.out.println("\n=========== Debt Simplification ====================");
//        manager.simplifyGroupDebts(hostelGroup.getGroupId());

        System.out.println("\n=========== printing Group-Specific Balances ====================");
        manager.showGroupBalances(hostelGroup.getGroupId());


        System.out.println("\n==========Attempting to remove Rohit from group==========");
        manager.removeUserFromGroup(user2.getUserId(), hostelGroup.getGroupId());

        System.out.println("\n======== Making Settlement to Clear Rohit's Debt ==========");
        manager.settlePaymentInGroup(hostelGroup.getGroupId(), user2.getUserId(), user3.getUserId(), BigDecimal.valueOf(200.0));

        System.out.println("\n======== Attempting to Remove Rohit Again ==========");
        manager.removeUserFromGroup(user2.getUserId(), hostelGroup.getGroupId());

        System.out.println("\n=========== Updated Group Balances ====================");
        manager.showGroupBalances(hostelGroup.getGroupId());
    }
}
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Initializing Zepto Flash Sale Architecture ===");

        // 1. Architecture Setup
        DarkStoreManager dsManager = new DarkStoreManager();
        OrderRoutingStrategy routingStrategy = new ProximityRoutingStrategy();
        OrderManager orderManager = new OrderManager(dsManager, routingStrategy);

        // 2. DarkStore Setup with Rules
        DarkStore storeA = new DarkStore("DarkStoreA", 0.0, 0.0);
        ThresholdReplenishStrategy strategyA = new ThresholdReplenishStrategy();

        strategyA.setOrUpdateRule(101, new ReplenishRule(5, 20));
        storeA.setReplenishStrategy(strategyA);

        System.out.println("\nInitial Stock -> Apples: 10, Bananas: 5");
        storeA.addStock(101, 10);
        storeA.addStock(102, 5);
        dsManager.registerDarkStore(storeA);

        // 3. Concurrency Chaos Engine
        int numberOfUsers = 100;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfUsers);
        CountDownLatch startingGun = new CountDownLatch(1);
        CountDownLatch finishLine = new CountDownLatch(numberOfUsers);

        AtomicInteger successfulOrders = new AtomicInteger(0);
        AtomicInteger failedOrders = new AtomicInteger(0);

        for (int i = 1; i <= numberOfUsers; i++) {
            final int userId = i;
            executor.submit(() -> {
                try {
                    User user = new User("User" + userId, 1.0, 1.0);
                    user.getCart().addItemToCart(101, 1); // 1 Apple
                    user.getCart().addItemToCart(102, 1); // 1 Banana

                    startingGun.await(); // Wait for the green light

                    boolean success = orderManager.placeOrder(user);
                    if (success) successfulOrders.incrementAndGet();
                    else failedOrders.incrementAndGet();

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    finishLine.countDown();
                }
            });
        }

        System.out.println("\n100 Virtual Users are ready at the checkout screen...");
        Thread.sleep(1000);
        System.out.println("GO! (Simulating 100 simultaneous checkouts)\n");

        // Fire the starting gun
        startingGun.countDown();

        // Wait for all orders to process
        finishLine.await();
        executor.shutdown();

        // 4. The Results
        System.out.println("\n=== Flash Sale Results ===");
        System.out.println("Successful Orders: " + successfulOrders.get() + " (Expected: 5)");
        System.out.println("Rolled Back Orders: " + failedOrders.get() + " (Expected: 95)");

        System.out.println("\n=== Final Physical Inventory Audit ===");
        System.out.println("Apples Remaining: " + storeA.checkStock(101) + " (Expected: 5)");
        System.out.println("Bananas Remaining: " + storeA.checkStock(102) + " (Expected: 0)");

        // Wait a few seconds to let the asynchronous delivery truck arrive
        System.out.println("\nWaiting for the delivery truck to arrive from the warehouse...");
        Thread.sleep(3000);

        System.out.println("\n=== Post-Delivery Physical Inventory Audit ===");
        System.out.println("Apples Remaining: " + storeA.checkStock(101) + " (Expected: 25)");
    }
}

/*
* user-> add to cart
*
* order manager (user): user->cart->items
* order manager -> dark store manager ->nearby stores
* order manager -> order routing strategy(items,store list)
*dark store have inventory manager
*
*
*
* */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DbInventoryStore implements InventoryStore{
    private final ConcurrentHashMap<Integer,Integer>stock=new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer,Product>products=new ConcurrentHashMap<>();

    @Override
    public void addProduct(Product product, int quantity) {
        products.putIfAbsent(product.getSku(),product);
        stock.merge(product.getSku(),quantity,Integer::sum);
    }

    @Override
    public List<Product> listAvailableProducts() {
        List<Product>available=new ArrayList<>();
        for(Map.Entry<Integer,Integer>entry:stock.entrySet()){
            if(entry.getValue()>0){
                available.add(products.get(entry.getKey()));
            }
        }
        return available;
    }

    @Override
    public int checkStock(int sku) {
        return stock.get(sku);
    }

    @Override
    public boolean tryDeductStock(int sku, int requestedQty) {
        while(true){
            int quantity=stock.get(sku);
            if(quantity==0 || requestedQty>quantity){
                return false;
            }
            // Atomic Compare-And-Swap guarantees thread safety
            if(stock.replace(sku,quantity,quantity-requestedQty)){
                return true;
            }
        }
    }
}

/*
This is one of the most brilliant and subtle parts of lock-free multithreading in Java!

Seeing a `while(true)` loop usually sets off alarm bells because it looks like an infinite loop that will freeze your program.
But in this specific case, it acts as a **Retry Mechanism** for something called **Optimistic Locking**.

Here is exactly why it is there, and what happens if you remove it.

### The Problem: What if `replace()` fails?

The magic of this method is the `stock.replace(sku, oldValue, newValue)` line.
This is a **Compare-And-Swap (CAS)** operation. It tells Java:

> *"Update the stock to `newValue`, BUT ONLY IF the stock is still exactly `oldValue`.
 If someone else changed it while I wasn't looking, do nothing and return `false`."*

Imagine Dark Store A has **10 Apples**.
User A and User B both hit checkout at the exact same millisecond.

**If we DID NOT have the `while(true)` loop:**

1. **User A** reads `currentQty` as 10.
2. **User B** reads `currentQty` as 10.
3. **User A** executes `replace(101, 10, 8)`. It succeeds! The stock is now 8. User A gets their apples.
4. **User B** executes `replace(101, 10, 7)`. **It fails.** The stock is no longer 10 (User A just changed it to 8). `replace` returns `false`.
5. Without a loop, the method would just end, and User B's order would fail... *even though there are still 8 apples left on the shelf!*

### The Solution: The `while(true)` Retry Loop

The `while(true)` loop fixes this by saying:
*"If another user beat you to the punch and changed the inventory before you could, don't give up!
Just grab the fresh inventory number and try again."*

Let's look at how the loop saves User B's order:

**Loop Iteration 1 for User B:**

1. User B reads `currentQty = 10`.
2. User A sneaks in and buys 2 apples. Real stock is now 8.
3. User B tries `replace(101, 10, 7)`. It fails (returns `false`).
4. **The loop spins back to the top.**

**Loop Iteration 2 for User B:**

1. User B reads `currentQty` again. This time it correctly gets **8**.
2. User B checks the business logic: Is 8 < 3? No, we still have enough.
3. User B tries `replace(101, 8, 5)`.
4. Nobody interrupted this time. It succeeds! `replace` returns `true`.
5. The `return true` statement completely breaks out of the `while(true)` loop. User B gets their apples.

### Is it dangerous? Will it loop forever?

No, it is mathematically guaranteed to break out.
It will only ever loop under two conditions, both of which forcefully exit the loop:

1. **Success:** The `replace` works, and it hits `return true;`.
2. **Failure:** The stock gets bought up by other people, drops below the `requestedQty`, and hits `return false;`.

In system design, this is called a **Spin-Wait** or **Spin-Lock**.
It is incredibly fast because it doesn't put the thread to sleep or lock up the entire database table;
it just spins around rapidly until it slips its update through perfectly.

*/

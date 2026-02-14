public class LoggerSingleton {
    private static volatile LoggerSingleton instance=null;

    private LoggerSingleton(){
        //this is done to prevent reflection
        if(instance!=null){
            throw new RuntimeException("Dont try to be oversmart");
        }
    }

    public static LoggerSingleton getInstance() {
        if(instance==null){
            synchronized (LoggerSingleton.class){
                if(instance==null){
                    instance=new LoggerSingleton();
                }
            }
        }
        return instance;
    }
}
//
//how java reflection can still access and define new instance even when ur constructor is private
//to prevent this we added the check inside constructor too
/*Constructor<LoggerSingleton> constructor = LoggerSingleton.class.getDeclaredConstructor();
constructor.setAccessible(true); // This bypasses the 'private' keyword
LoggerSingleton secondInstance = constructor.newInstance();
*/

//Static: Ensures the variable belongs to the class, not a specific object.
//Volatile: This is crucial for double-checked locking.
// It ensures that changes made to instance by one thread are immediately visible to all other threads.
// It also prevents "instruction reordering" by the compiler, which could otherwise lead to a thread accessing
// a partially initialized object.

//This is the most efficient way to implement a thread-safe, lazy-initialized Singleton.
//First Check: if (instance == null)
//If the instance already exists, we skip the expensive synchronized block entirely. This improves performance significantly.
//Synchronization: synchronized(LoggerSingleton.class)
//If the instance is null, we enter a synchronized block. Only one thread can enter this section at a time.
//Second Check: if (instance == null)
//Inside the synchronized block, we check again. This is because another thread might have initialized the instance while the current thread was waiting to enter the synchronized block.
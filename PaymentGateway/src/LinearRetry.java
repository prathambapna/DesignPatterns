public class LinearRetry implements RetryStrategy{
    private final long delayMs;

    public LinearRetry(long delayMs){
        this.delayMs=delayMs;
    }
    @Override
    public void wait(int attempt) {
        System.out.println(" -> [linear retry strategy] : waiting for : "+ delayMs + "ms");
        try{
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

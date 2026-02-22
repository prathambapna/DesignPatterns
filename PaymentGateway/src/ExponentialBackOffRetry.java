public class ExponentialBackOffRetry implements RetryStrategy{
    private final long baseDelayMs;

    public ExponentialBackOffRetry(long baseDelayMs){
        this.baseDelayMs=baseDelayMs;
    }
    @Override
    public void wait(int attempt) {
        long delay = (long) (baseDelayMs * Math.pow(2, attempt - 1));
        System.out.println("  -> [Exponential Strategy] Waiting " + delay + "ms...");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

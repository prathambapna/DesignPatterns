public interface RetryStrategy {
    void wait(int attempt);
}

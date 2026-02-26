class ReplenishRule {
    public final int threshold;
    public final int batchSize;
    public ReplenishRule(int threshold, int batchSize) {
        this.threshold = threshold;
        this.batchSize = batchSize;
    }
}
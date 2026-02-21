public abstract class MoneyHandler {
    protected MoneyHandler nextHandler = null;
    abstract void dispense(int amount);
    public void setNextHandler(MoneyHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

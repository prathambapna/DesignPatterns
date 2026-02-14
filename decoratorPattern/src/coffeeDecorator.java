public abstract class coffeeDecorator implements coffee{
    protected coffee decoratedCoffee;
    coffeeDecorator(coffee decoratedCoffee){
        this.decoratedCoffee=decoratedCoffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

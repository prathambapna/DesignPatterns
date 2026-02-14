public class milkDecorator extends coffeeDecorator{
    public milkDecorator(coffee c){
        super(c);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription()+" + Milk";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost()+20;
    }
}

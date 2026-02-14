public class sugarDecorator extends coffeeDecorator{
    public sugarDecorator(coffee c){
        super(c);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription()+" + sugar";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost()+10;
    }
}

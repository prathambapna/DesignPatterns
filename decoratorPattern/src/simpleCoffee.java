public class simpleCoffee implements coffee {
    @Override
    public String getDescription(){
        return "simple coffee";
    }
    @Override
    public double getCost(){
        return 100.0;
    }
}

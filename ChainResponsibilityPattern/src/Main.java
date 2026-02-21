//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MoneyHandler thousandHandler=new ThousandHandler(3);
        MoneyHandler fiveHundredHandler=new FiveHundredHandler(2);
        MoneyHandler hundredHandler=new HundredHandler(8);

        thousandHandler.setNextHandler(fiveHundredHandler);
        fiveHundredHandler.setNextHandler(hundredHandler);

        System.out.println("Request for 4100");
        thousandHandler.dispense(4100);

        System.out.println("Request for 2000");
        thousandHandler.dispense(2000);
    }
}
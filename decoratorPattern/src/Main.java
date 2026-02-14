//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        coffee c=new simpleCoffee();
        System.out.println(c.getDescription());

        c=new milkDecorator(c);
        System.out.println(c.getDescription());

        c=new sugarDecorator(c);
        System.out.println(c.getDescription());

        c=new milkDecorator(c);
        System.out.println(c.getDescription());
    }
}
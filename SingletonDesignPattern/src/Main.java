//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LoggerSingleton obj1=LoggerSingleton.getInstance();
        System.out.println(obj1);

        LoggerSingleton obj2=LoggerSingleton.getInstance();
        System.out.println(obj2);
    }
}
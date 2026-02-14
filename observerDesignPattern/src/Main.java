//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        NewsChannel newschannel=new NewsChannel();

        Observer shyam=new mobileUser("shyam");
        Observer ram=new mobileUser("ram");

        newschannel.subscribe(shyam);
        newschannel.subscribe(ram);

        newschannel.uploadNews("budget announced");

        newschannel.unsubscribe(ram);
        newschannel.uploadNews("ipl team announced");
    }
}
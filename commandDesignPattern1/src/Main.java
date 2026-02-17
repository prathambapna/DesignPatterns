//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        RemoteController remoteController=new RemoteController();
        Light light=new Light();
        Fan fan=new Fan();
        LightCommand lightCommand=new LightCommand(light);
        FanCommand fanCommand=new FanCommand(fan);

        remoteController.setCommand(lightCommand,0);
        remoteController.setCommand(fanCommand,1);

        remoteController.pressButton(0);
        remoteController.pressButton(1);
        remoteController.pressButton(2);
        remoteController.pressButton(0);
        remoteController.pressButton(1);
        remoteController.pressButton(1);
        remoteController.pressButton(1);
        remoteController.pressButton(1);
        remoteController.pressButton(1);
        remoteController.pressButton(1);
        remoteController.pressButton(0);
    }
}
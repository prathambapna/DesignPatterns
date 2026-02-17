public class FanCommand implements Command{
    private final Fan fan;

    private final int highSpeed;
    public FanCommand(Fan fan){
        this.fan=fan;
        this.highSpeed=fan.getHighSpeed();
    }

    @Override
    public void execute() {
        int speed=(fan.getSpeed()+1)%(highSpeed+1);
        fan.setSpeed(speed);
    }
}

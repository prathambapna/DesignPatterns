public class Fan {
    public final static int highSpeed=5;
    public int speed;
    public Fan(){
        this.speed=0;
    }
    public int getSpeed(){
        return speed;
    }
    public int getHighSpeed(){
        return highSpeed;
    }
    void setSpeed(int speed){
        this.speed=speed;
        System.out.println("Fan speed: "+speed);
    }
}

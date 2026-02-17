public class Light {
    private  boolean isOn;
    public Light(){
        this.isOn=false;
    }

    public boolean getIsOn() {
        return isOn;
    }
    public void setIsOn(){
        isOn=!(isOn);
        if(isOn){
            System.out.println("Light on");
        }
        else{
            System.out.println("light off");

        }
    }
}

public class DeviceManager {

    private OutputDevice outputDevice;
    private static volatile DeviceManager instance;
    private DeviceManager(){
        if(instance!=null){
            throw new RuntimeException("Don't try to be over smart");
        }
    }

    public static DeviceManager getInstance() {
        if(instance==null){
            synchronized (DeviceManager.class){
                if(instance==null){
                    instance=new DeviceManager();
                }
            }
        }
        return instance;
    }

    public OutputDevice getActiveOutputDevice() {
        return outputDevice;
    }

    public OutputDevice connectDevice(DeviceType dt){
        if(dt==DeviceType.BLUETOOTH){
            return this.outputDevice=new BluetoothAdapter(new BluetoothApi());
        }
        else if(dt==DeviceType.HEADPHONE){
            return this.outputDevice=new HeadphoneAdapter(new HeadphoneApi());
        }
        else{
            return null;
        }
    }

}

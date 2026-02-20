public class BluetoothAdapter implements OutputDevice{
    private final BluetoothApi bluetoothApi;

    public BluetoothAdapter(BluetoothApi bluetoothApi){
        this.bluetoothApi=bluetoothApi;
        System.out.println("Bluetooth device connected");
    }

    @Override
    public void playAudio(Song song, int timestamp) {
        bluetoothApi.playSongWithBluetooth(song,timestamp);
    }

    @Override
    public void stopAudio() {
        bluetoothApi.stopAudioOnBluetooth();
    }

    @Override
    public int getCurrentTimeStamp() {
        return bluetoothApi.getCurrentTimeStamp();
    }
}

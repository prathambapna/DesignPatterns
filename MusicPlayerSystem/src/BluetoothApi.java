public class BluetoothApi {
    private int timestamp;
    public void playSongWithBluetooth(Song song,int timestamp){
        System.out.println("playing song on bluetooth: "+song.getTitle());
        this.timestamp=timestamp;
    }

    public int getCurrentTimeStamp() {
        return timestamp+5;
    }

    public void stopAudioOnBluetooth() {
        System.out.println("stopping song on bluetooth: ");
    }
}

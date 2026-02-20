public class HeadphoneApi {
    private int timestamp;
    public void playSongWithHeadphone(Song song,int timestamp){
        System.out.println("playing song on Headphone: "+song.getTitle());
        this.timestamp=timestamp;
    }

    public int getCurrentTimeStamp() {
        return timestamp+5;
    }

    public void stopAudioOnBluetooth() {
        System.out.println("stopping song on Headphone");
    }
}

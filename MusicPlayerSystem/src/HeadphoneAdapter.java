public class HeadphoneAdapter implements OutputDevice{
    private final HeadphoneApi headphoneApi;

    public HeadphoneAdapter(HeadphoneApi headphoneApi){
        this.headphoneApi=headphoneApi;
        System.out.println("Headphone device connected");
    }

    @Override
    public void playAudio(Song song, int timestamp) {
        headphoneApi.playSongWithHeadphone(song,timestamp);
    }

    public void stopAudio() {
        headphoneApi.stopAudioOnBluetooth();
    }

    @Override
    public int getCurrentTimeStamp() {
        return headphoneApi.getCurrentTimeStamp();
    }
}

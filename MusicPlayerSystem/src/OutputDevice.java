public interface OutputDevice {
    void playAudio(Song song, int timestamp);
    void stopAudio();
    int getCurrentTimeStamp();
}

import java.util.LinkedList;
import java.util.Queue;

public class AudioEngine {
    private Song currentSong;
    private OutputDevice currentDevice;
    private PlayStrategy playStrategy;
    private final Queue<Song>userAddedSongsInQueue;
    private boolean isPlaying;
    private int currentTimeStamp;

    public AudioEngine(){
        userAddedSongsInQueue=new LinkedList<>();
        this.isPlaying=false;
        this.currentTimeStamp=0;
    }
    public void setCurrentDevice(OutputDevice outputDevice){
        this.currentDevice=outputDevice;
        if (isPlaying && currentSong != null) {
            this.play();
        }
    }

    public void setCurrentSong(Song song){
        this.currentSong=song;
    }

    public void addSongToQueue(Song song){
        if (song != null) {
            this.userAddedSongsInQueue.add(song);
            System.out.println("Added to queue: " + song.getTitle());
        }
    }

    public int getCurrentTimeStamp() {
        return currentDevice.getCurrentTimeStamp();
    }

    public void setPlayStrategy(PlayStrategy strategy){
        playStrategy=strategy;
    }

    public void play(){
        if (currentSong == null) {
            System.out.println("No song selected to play.");
            return;
        }
        if (currentDevice == null) {
            System.out.println("No output device connected.");
            return;
        }

        currentDevice.playAudio(currentSong, this.currentTimeStamp);
        this.isPlaying = true;
//        System.out.println("Playing: " + currentSong.getTitle() + " at " + currentTimeStamp + "s");
    }

    public void pause() {
        if (isPlaying && currentDevice != null) {
            // 1. Ask hardware exactly where it stopped
            this.currentTimeStamp = currentDevice.getCurrentTimeStamp();

            // 2. Tell hardware to stop outputting sound
            currentDevice.stopAudio();

            this.isPlaying = false;
            System.out.println("Paused at " + currentTimeStamp + "s");
        }
    }

    public void playNextSong(){
        Song nextSongToPlay=null;

        if(!userAddedSongsInQueue.isEmpty()){
            nextSongToPlay=userAddedSongsInQueue.poll();
        }
        else if(playStrategy!=null && playStrategy.hasNext()){
            nextSongToPlay=playStrategy.nextSong();
        }

        if(nextSongToPlay!=null){
            this.currentSong=nextSongToPlay;
            this.currentTimeStamp=0;
            this.play();
        }
        else{
            System.out.println("No more song to play");
            this.stopCompletely();
        }

    }

    private void stopCompletely() {
        if (currentDevice != null) {
            currentDevice.stopAudio();
        }
        this.isPlaying = false;
        this.currentTimeStamp = 0;
        this.currentSong = null;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public int getCurrentTimestamp() {
        // If actively playing, get the live time. If paused, return the saved time.
        return isPlaying ? currentDevice.getCurrentTimeStamp() : currentTimeStamp;
    }
}

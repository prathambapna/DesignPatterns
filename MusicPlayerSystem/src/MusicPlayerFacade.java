public class MusicPlayerFacade {
    private static volatile MusicPlayerFacade instance;
    private final AudioEngine audioEngine;
    private final DeviceManager deviceManager;
    private final PlayListManager playListManager;
    private final LibraryManager libraryManager;
    private final StrategyFactory strategyFactory;
    private MusicPlayerFacade(){
        if(instance!=null){
            throw new RuntimeException("Dont try to be smart");
        }
        audioEngine=new AudioEngine();
        deviceManager=DeviceManager.getInstance();
        playListManager=PlayListManager.getInstance();
        libraryManager=LibraryManager.getInstance();
        strategyFactory=new StrategyFactory();
    }

    public static MusicPlayerFacade getInstance(){
        if (instance == null) {
            synchronized (MusicPlayerFacade.class){
                if(instance==null){
                    instance=new MusicPlayerFacade();
                }
            }
        }
        return instance;
    }

    public Song importSong(String title , String artist , String path){
        return libraryManager.createAndStoreSong(path,artist,title);
    }

    public void createPlaylist(String playlistName){
        playListManager.createPlayList(playlistName);
    }

    public void addSongToPlaylist(Song song , String playlistName){
        Playlist playlist=playListManager.getPlaylist(playlistName);
        playListManager.addSongToPlaylist(playlist,song);
    }

    public void connectDevice(DeviceType dt){
        audioEngine.setCurrentDevice(deviceManager.connectDevice(dt));
    }

    public void addSongInQueue(Song song){
        audioEngine.addSongToQueue(song);
    }

    public void playNext(){
        audioEngine.playNextSong();
    }

    public void play(){
        audioEngine.play();
    }

    public void pause(){
        audioEngine.pause();
    }

    public void loadAndPlayPlaylist(String playListName, StrategyType st){
        Playlist playlist=playListManager.getPlaylist(playListName);
        audioEngine.setPlayStrategy(strategyFactory.getStrategy(st,playlist));
        audioEngine.setCurrentSong(playlist.getSongFromPlaylist(0));
        System.out.println("Playing playlist_name: "+playListName);
        this.play();
    }


}

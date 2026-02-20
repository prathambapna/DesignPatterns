import java.util.HashMap;

public class PlayListManager {
    private final HashMap<String , Playlist>userPlayLists=new HashMap<>();
    private static volatile PlayListManager instance;

    private PlayListManager(){
        if(instance!=null){
            throw new RuntimeException("Don't try to be smart");
        }
    }

    public static PlayListManager getInstance(){
        if(instance==null){
            synchronized (PlayListManager.class){
                if(instance==null){
                    instance=new PlayListManager();
                }
            }
        }
        return instance;
    }

    public void createPlayList(String playListName){
        Playlist playlist=new Playlist(playListName);
        userPlayLists.put(playListName,playlist);
    }

    public void addSongToPlaylist(Playlist playlist,Song song){
        playlist.addSong(song);
    }

    public Playlist getPlaylist(String playListName){
        return userPlayLists.get(playListName);
    }
}

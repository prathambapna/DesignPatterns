import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private final String playListName;
    private List<Song>songList=new ArrayList<>();

    public Playlist(String playListName){
        this.playListName=playListName;
    }

    public void addSong(Song song){
        if(songList.contains(song)){
            System.out.println("Song: "+song.getTitle() + " already present in playlist: "+ playListName);
            return;
        }
        songList.add(song);
        System.out.println("Song: "+song.getTitle() + " added to playlist: "+ playListName);
    }

    public void removeSong(Song song){
        if(songList.contains(song)){
            songList.remove(song);
            System.out.println("Song: "+song.getTitle() + " removed from playlist: "+ playListName);
            return;
        }
        System.out.println("Song: "+song.getTitle() + " not present in playlist: "+ playListName);
    }

    public List<Song> getSongList() {
        return songList;
    }

    public Song getSongFromPlaylist(int index){
        return songList.get(index);
    }

    public int totalSongsInPlaylist(){
        return songList.size();
    }
}

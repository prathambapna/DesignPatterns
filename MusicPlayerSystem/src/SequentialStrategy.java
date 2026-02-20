import java.util.List;

public class SequentialStrategy implements PlayStrategy{
    private final Playlist playlist;
    private final int totalSongs;
    private int index;

    public SequentialStrategy(Playlist playlist){
        this.playlist=playlist;
        totalSongs=playlist.totalSongsInPlaylist();
        index=0;
    }

    @Override
    public Song nextSong() {
        index+=1;
        return playlist.getSongFromPlaylist(index);
    }

    @Override
    public boolean hasNext() {
        return (index+1)<totalSongs;
    }
}

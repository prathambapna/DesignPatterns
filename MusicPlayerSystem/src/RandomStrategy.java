import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomStrategy implements PlayStrategy{
    private final Playlist playlist;
    private final int totalSongs;
    private final List<Integer>randomIndex;
    private static int index;

    public RandomStrategy(Playlist playlist){
        this.playlist=playlist;
        totalSongs=playlist.totalSongsInPlaylist();

        randomIndex=new ArrayList<>();
        index=0;
        for (int i = 0; i < totalSongs; i++) {
            randomIndex.add(i);
        }
        // Shuffle the list
        Collections.shuffle(randomIndex);

    }

    public Song nextSong(){
        index+=1;
        return playlist.getSongFromPlaylist(randomIndex.get(index));
    }

    @Override
    public boolean hasNext() {
        return (index+1)<totalSongs;
    }

}

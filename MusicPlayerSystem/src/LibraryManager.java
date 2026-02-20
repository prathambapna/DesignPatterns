import java.util.HashMap;
import java.util.Random;

public class LibraryManager {
    private final HashMap<String,Song>songMap=new HashMap<>();
    private static volatile LibraryManager instance;

    private LibraryManager(){
        if(instance!=null){
            throw new RuntimeException("Dont't try to be smart");
        }
    }

    public static LibraryManager getInstance(){
        if(instance==null){
            synchronized (LibraryManager.class){
                if(instance==null){
                    instance=new LibraryManager();
                }
            }
        }
        return instance;
    }

    public Song createAndStoreSong(String path,String artist, String title){
        //logic for calculation of duration, for now we will keep it to random number between 100 and 200sec
        int randomNumber=new Random().nextInt(101) + 100;
        Song s=new Song(title,artist,path,randomNumber);
        songMap.put(title+" "+artist,s);
        return s;
    }

    public Song getSong(String songName){
        return songMap.get(songName);
    }

}

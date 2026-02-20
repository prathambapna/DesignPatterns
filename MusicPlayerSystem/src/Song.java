public class Song {
    private static int counter=0;
    private final String title;
    private final String artist;
    private final String path;
    private final int id;
    private final int durationInSeconds;
    public Song(String title,String artist,String path,int durationInSeconds){
        this.artist=artist;
        this.durationInSeconds=durationInSeconds;
        this.path=path;
        this.title=title;
        counter+=1;
        this.id=counter;
    }
    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public String getTitle() {
        return this.title;
    }
}

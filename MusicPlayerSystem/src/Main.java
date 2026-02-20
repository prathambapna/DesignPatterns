//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MusicPlayerFacade musicPlayerFacade=MusicPlayerFacade.getInstance();

        Song s1 = musicPlayerFacade.importSong("Tum Hi Ho", "Arijit Singh", "p1/s2");
        Song s2 = musicPlayerFacade.importSong("Agar Tum Saath Ho", "Alka Yagnik", "p1/s3");
        Song s3 = musicPlayerFacade.importSong("Channa Mereya", "Arijit Singh", "p1/s4");
        Song s4 = musicPlayerFacade.importSong("Dil Diyan Gallan", "Atif Aslam", "p1/s5");
        Song s5 = musicPlayerFacade.importSong("Teri Mitti", "B Praak", "p1/s6");
        Song s6 = musicPlayerFacade.importSong("Ghoomar", "Shreya Ghoshal", "p1/s7");
        Song s7 = musicPlayerFacade.importSong("Kabira", "Tochi Raina", "p1/s8");
        Song s8 = musicPlayerFacade.importSong("Zaalima", "Arijit Singh", "p1/s9");
        Song s9 = musicPlayerFacade.importSong("Kal Ho Naa Ho", "Sonu Nigam", "p1/s10");
        Song s10 = musicPlayerFacade.importSong("Desi Girl", "Sunidhi Chauhan", "p1/s11");

        musicPlayerFacade.createPlaylist("Playlist 1");
        musicPlayerFacade.createPlaylist("Playlist 2");

        musicPlayerFacade.addSongToPlaylist(s1,"Playlist 1");
        musicPlayerFacade.addSongToPlaylist(s2,"Playlist 1");
        musicPlayerFacade.addSongToPlaylist(s3,"Playlist 1");
        musicPlayerFacade.addSongToPlaylist(s4,"Playlist 2");
        musicPlayerFacade.addSongToPlaylist(s5,"Playlist 2");
        musicPlayerFacade.addSongToPlaylist(s6,"Playlist 2");
        musicPlayerFacade.addSongToPlaylist(s7,"Playlist 2");

        musicPlayerFacade.connectDevice(DeviceType.BLUETOOTH);
        musicPlayerFacade.loadAndPlayPlaylist("Playlist 1",StrategyType.SEQUENTIAL);

        musicPlayerFacade.addSongInQueue(s9);
        musicPlayerFacade.pause();
        musicPlayerFacade.playNext();
        musicPlayerFacade.playNext();
        musicPlayerFacade.playNext();
        musicPlayerFacade.playNext();


        musicPlayerFacade.loadAndPlayPlaylist("Playlist 2",StrategyType.RANDOM);
        musicPlayerFacade.addSongInQueue(s10);
        musicPlayerFacade.pause();
        musicPlayerFacade.play();
        musicPlayerFacade.playNext();

        musicPlayerFacade.connectDevice(DeviceType.HEADPHONE);
        musicPlayerFacade.playNext();
        musicPlayerFacade.playNext();
        musicPlayerFacade.playNext();
    }
}
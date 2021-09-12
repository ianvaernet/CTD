package Clase5_Proxy;

public class DownloadService implements Downloader{

    public Song downloadSong(User user, String songName) {
        System.out.println("Downloading " + songName + "...");
        try {
            Thread.sleep(5000);
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
        System.out.println("Download finished");
        return new Song(songName, Math.random() * 6);
    }
}

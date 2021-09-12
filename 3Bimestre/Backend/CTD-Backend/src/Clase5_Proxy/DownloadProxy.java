package Clase5_Proxy;

import java.util.HashMap;
import java.util.Map;

public class DownloadProxy implements Downloader {
    private Downloader downloadService;
    private final Map<String, Song> songsInCache;

    public DownloadProxy() {
        songsInCache = new HashMap<>();
    }

    @Override
    public Song downloadSong(User user, String songName) {
        if (user.getUserType() != UserType.PREMIUM) {
            System.out.println("Only premium users can download songs");
            return null;
        }

        Song songInCache = songsInCache.get(songName);
        if (songInCache != null) {
            System.out.println("Song found in cache");
            return songInCache;
        }

        if (downloadService == null) downloadService = new DownloadService();
        Song downloadedSong = downloadService.downloadSong(user, songName);
        songsInCache.put(songName, downloadedSong);
        return downloadedSong;
    }
}

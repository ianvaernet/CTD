package Clase5_Proxy;

public class Main {
    public static void main(String[] args) {
        User freeUser = new User(UserType.FREE, "ianv97");
        User premiumUser = new User(UserType.PREMIUM, "ianvaernet");

        Downloader downloader = new DownloadProxy();
        downloader.downloadSong(freeUser, "Imagine Dragons - Demons");
        downloader.downloadSong(premiumUser, "Imagine Dragons - Demons");
        downloader.downloadSong(premiumUser, "Imagine Dragons - Demons");
    }
}

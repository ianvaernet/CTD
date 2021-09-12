package Clase4_ChainOfResponsability;

public class DB {
    public static User findUser(String username) {
        if (username.equals("ianvaernet")) {
            return new User("Ian", "Vaernet", "ianvaernet@gmail.com", "ianvaernet", "asd123", "USER");
        }
        return null;
    }
}

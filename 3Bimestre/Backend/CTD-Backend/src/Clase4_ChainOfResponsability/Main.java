package Clase4_ChainOfResponsability;

public class Main {
    public static void main(String[] args) {
        User user = DB.findUser("ianvaernet");
        AuthMiddleware authMiddleware = new AuthMiddleware();
        if (authMiddleware.isAuthorized(user, "asd123", "USER")) System.out.println("User authorized");
        if (authMiddleware.isAuthorized(user, "asd1234", "USER")) System.out.println("User authorized");
        if (authMiddleware.isAuthorized(user, "asd123", "ADMIN")) System.out.println("User authorized");
    }
}

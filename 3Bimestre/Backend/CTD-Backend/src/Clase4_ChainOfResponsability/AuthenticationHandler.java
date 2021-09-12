package Clase4_ChainOfResponsability;

public class AuthenticationHandler extends Handler {
    public AuthenticationHandler(){}

    protected boolean isAuthorized(User user, String password, String roleRequired) {
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid username or password");
            return false;
        }
        if (nextHandler == null) return true;
        return nextHandler.isAuthorized(user, password, roleRequired);
    }
}

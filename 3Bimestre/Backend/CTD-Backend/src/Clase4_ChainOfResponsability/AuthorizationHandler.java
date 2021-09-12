package Clase4_ChainOfResponsability;

public class AuthorizationHandler extends Handler {
    public AuthorizationHandler() {}

    protected boolean isAuthorized(User user, String password, String roleRequired) {
        if (!user.getRole().equals(roleRequired)) {
            System.out.println("User not authorized");
            return false;
        }
        if (nextHandler == null) return true;
        return nextHandler.isAuthorized(user, password, roleRequired);
    }
}

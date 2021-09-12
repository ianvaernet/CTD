package Clase4_ChainOfResponsability;

public class AuthMiddleware {
    private Handler initialHandler;

    public AuthMiddleware() {
        this.initialHandler = new AuthenticationHandler();
        Handler authorizationHandler = new AuthorizationHandler();
        this.initialHandler.setNextHandler(authorizationHandler);
    }

    public boolean isAuthorized(User user, String password, String roleRequired) {
        return initialHandler.isAuthorized(user, password, roleRequired);
    }
}

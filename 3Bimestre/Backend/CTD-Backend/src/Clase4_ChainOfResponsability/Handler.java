package Clase4_ChainOfResponsability;

public abstract class Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract boolean isAuthorized(User user, String password, String roleRequired);
}

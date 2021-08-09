package Clase25_Observer;

public interface Observable {
    public void agregar(Observador observador);
    public void quitar(Observador observador);
    public void notificar();
}

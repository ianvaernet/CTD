package Clase26_Strategy;

public class Recorrido {
    private Punto punto1;
    private Punto punto2;
    private Strategy medio;

    public Recorrido(Punto punto1, Punto punto2, Strategy medio) {
        this.punto1 = punto1;
        this.punto2 = punto2;
        this.medio = medio;
    }

    public void setMedio(Strategy medio) {
        this.medio = medio;
    }

    public Double calcularTiempo() {
        return medio.calcularTiempo(punto1, punto2);
    }
}

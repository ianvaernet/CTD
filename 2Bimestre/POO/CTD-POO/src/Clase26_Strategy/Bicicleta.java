package Clase26_Strategy;

public class Bicicleta implements Strategy {
    @Override
    public Double calcularTiempo(Punto punto1, Punto punto2) {
        return punto1.distanciaA(punto2) / 0.050366 * 1.5;
    }
}

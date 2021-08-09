package Clase26_Strategy;

public class Caminando implements Strategy {
    @Override
    public Double calcularTiempo(Punto punto1, Punto punto2) {
        return punto1.distanciaA(punto2) / 0.20366 * 13;
    }
}

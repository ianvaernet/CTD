package Clase26_Strategy;

public class Punto {
    private Double latitud;
    private Double longitud;

    public Punto(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public Double distanciaA(Punto otroPunto) {
        return Math.sqrt(Math.pow(this.latitud - otroPunto.getLatitud(), 2) + Math.pow(this.longitud - otroPunto.getLongitud(), 2));
    }
}

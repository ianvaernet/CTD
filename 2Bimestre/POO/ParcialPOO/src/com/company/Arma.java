package com.company;

public abstract class Arma {
    private Integer cantidadMuniciones;
    private Double alcanceEnMts;
    private String marca;
    private Double calibre;
    private EstadoArma estado;
    private Policia policia;

    public Arma(Integer cantidadMuniciones, Double alcanceEnMts, String marca, Double calibre, EstadoArma estado, Policia policia) {
        this.cantidadMuniciones = cantidadMuniciones;
        this.alcanceEnMts = alcanceEnMts;
        this.marca = marca;
        this.calibre = calibre;
        this.estado = estado;
        this.policia = policia;
    }

    public Double getAlcanceEnMts() {
        return alcanceEnMts;
    }
    public Boolean estaEnCondiciones() {
        return estado.equals(EstadoArma.EnUso) && calibre >= 9;
    }
    public String toString() {
        return "Policia: \n" + policia + "\nArma:\n -CantidadMuniciones: " + cantidadMuniciones + "\n -AlcanceEnMts: " + alcanceEnMts + "\n -Marca: " + marca + "\n -Calibre: " + calibre + "\n -Estado: " + estado + "\n -Está en condiciones: " + estaEnCondiciones();
    }
}

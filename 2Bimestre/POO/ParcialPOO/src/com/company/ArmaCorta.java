package com.company;

public class ArmaCorta extends Arma {
    private Boolean automatica;

    public ArmaCorta(Integer cantidadMuniciones, Double alcanceEnMts, String marca, Double calibre, EstadoArma estado, Policia policia, Boolean automatica) {
        super(cantidadMuniciones, alcanceEnMts, marca, calibre, estado, policia);
        this.automatica = automatica;
    }
    public Boolean tieneMedianoAlcance() {
        return getAlcanceEnMts() > 200;
    }

}

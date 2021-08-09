package com.company;

public class ArmaLarga extends Arma implements Comparable<ArmaLarga> {
    private Boolean tieneSello;
    private String descripcion;
    private Integer nivel;

    public ArmaLarga(Integer cantidadMuniciones, Double alcanceEnMts, String marca, Double calibre, EstadoArma estado, Policia policia, Boolean tieneSello, String descripcion, Integer nivel) {
        super(cantidadMuniciones, alcanceEnMts, marca, calibre, estado, policia);
        this.tieneSello = tieneSello;
        this.descripcion = descripcion;
        this.nivel = nivel;
    }

    @Override
    public int compareTo(ArmaLarga otraArma) {
        if (this.nivel < otraArma.nivel) return -1;
        else if (this.nivel > otraArma.nivel) return 1;
        return 0;
    }
}

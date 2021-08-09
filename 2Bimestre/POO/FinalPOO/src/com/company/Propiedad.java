package com.company;

public abstract class Propiedad {
    private String calle;
    private Integer numero;

    public Propiedad(String calle, Integer numero) {
        this.calle = calle;
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public String toString() {
        return " PROPIEDAD " + hashCode() + ": \n  Dirección: " + this.calle + " " + this.numero + "\n  Impuestos: " + this.calcularImpuesto();
    }

    public abstract Double calcularImpuesto();
}

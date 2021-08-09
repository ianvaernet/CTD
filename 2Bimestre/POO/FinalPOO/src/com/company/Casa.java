package com.company;

public class Casa extends Propiedad {
    private Double impuestoBase;

    public Casa(String calle, Integer numero, Double impuestoBase) {
        super(calle, numero);
        this.impuestoBase = impuestoBase;
    }

    public Double calcularImpuesto() {
        if (getCalle().equals("Av. San Martín")) return this.impuestoBase * 1.10;
        return this.impuestoBase;
    }
}

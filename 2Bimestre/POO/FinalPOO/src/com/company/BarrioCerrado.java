package com.company;

import java.util.ArrayList;
import java.util.List;

public class BarrioCerrado extends Propiedad {
    private Integer multiplicadorImpuesto;
    private List<Propiedad> propiedades;

    public BarrioCerrado(String calle, Integer numero, Integer multiplicadorImpuesto) {
        super(calle, numero);
        this.multiplicadorImpuesto = multiplicadorImpuesto;
        this.propiedades = new ArrayList<>();
    }

    public void agregarPropiedad(Propiedad propiedad) {
        this.propiedades.add(propiedad);
    }

    public Double calcularImpuesto() {
        Double acumulador = 0.0;
        for (Propiedad propiedad : propiedades) {
            acumulador += propiedad.calcularImpuesto();
        }
        return acumulador * this.multiplicadorImpuesto;
    }
}

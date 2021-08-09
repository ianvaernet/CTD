package com.company;

public class Test {

    public static void main(String[] args) {
	    Municipio resistencia = new Municipio("Resistencia");

	    resistencia.agregarPropiedad(PropiedadFactory.CASA_SAN_MARTIN);
	    resistencia.agregarPropiedad(PropiedadFactory.CASA_MITRE);
	    resistencia.agregarPropiedad(PropiedadFactory.BARRIO_CERRADO_GUTIERRES);

	    resistencia.mostrarPropiedades();
    }
}

package com.company;

import java.util.ArrayList;
import java.util.List;

public class Municipio {
    private String nombre;
    private List<Propiedad> propiedades;

    public Municipio(String nombre) {
        this.nombre = nombre;
        this.propiedades = new ArrayList<>();
    }

    public void agregarPropiedad(String codigoPropiedad) {
        this.propiedades.add(PropiedadFactory.getInstance().crearPropiedad(codigoPropiedad));
    }

    public void mostrarPropiedades() {
        System.out.println("Propiedades en el " + this.toString() + ":");
        for (Propiedad propiedad : propiedades) {
            System.out.println(propiedad);
        }
    }

    public String toString() {
        return "Municipio de " + this.nombre;
    }
}

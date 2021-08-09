package com.company;

public class Policia {
    private String nombre;
    private String apellido;
    private Integer legajo;

    public Policia(String nombre, String apellido, Integer legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
    }
    @Override
    public String toString() {
        return " -Nombre: " + nombre + "\n -Apellido: " + apellido + "\n -Legajo: " + legajo;
    }
}

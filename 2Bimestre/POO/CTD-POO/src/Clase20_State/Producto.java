package Clase20_State;

public class Producto {
    private String descripcion;
    private Double precio;

    public Producto(String descripcion, Double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }
    public String toString() {
        return this.descripcion;
    }
}

package Clase1_Facade;

public class ApiProducto {
    public int descuento(Producto producto) {
        if (producto.getTipo().equals("Lata")) return 10;
        return 0;
    }
}

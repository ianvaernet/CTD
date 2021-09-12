package Clase19_Singleton_FactoryMethod;

import java.util.ArrayList;

public class Almacen {
    private static Almacen instance;
    private ArrayList<Producto> productos;

    private Almacen() {
        this.productos = new ArrayList<Producto>();
    }

    public static Almacen getInstance() {
        if (instance == null) instance = new Almacen();
        return instance;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public Double calcularEspacioNecesario() {
        Double espacioTotal = 0.0;
        for (Producto producto : productos) {
            espacioTotal += producto.calcularEspacio();
        }
        return espacioTotal;
    }
}

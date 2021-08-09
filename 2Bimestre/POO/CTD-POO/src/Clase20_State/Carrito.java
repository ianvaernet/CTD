package Clase20_State;

import Clase20_State.Estados.Vacio;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private final List<Producto> productos;
    private Estado estadoActual;

    public Carrito() {
        this.estadoActual = new Vacio();
        this.productos = new ArrayList<Producto>();
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        estadoActual = estadoActual.agregarProducto(this, producto);
    }

    public void cancelarCarrito() {
        productos.clear();
        estadoActual = estadoActual.cancelarCarrito();
    }

    public void volver() {
        estadoActual = estadoActual.volver(this);
    }

    public void siguiente() {
        estadoActual = estadoActual.siguiente();
    }
}

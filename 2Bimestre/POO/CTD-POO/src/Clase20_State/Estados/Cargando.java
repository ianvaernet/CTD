package Clase20_State.Estados;

import Clase20_State.Carrito;
import Clase20_State.Estado;
import Clase20_State.Producto;

public class Cargando implements Estado {
    public Estado agregarProducto(Carrito carrito, Producto producto) {
        carrito.getProductos().add(producto);
        return this;
    }

    public Estado cancelarCarrito() {
        return new Vacio();
    }

    public Estado volver(Carrito carrito) {
        carrito.getProductos().clear();
        return new Vacio();
    }

    public Estado siguiente() {
        return new Pagando();
    }

    @Override
    public String toString() {
        return "Cargando";
    }
}

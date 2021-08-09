package Clase20_State.Estados;

import Clase20_State.Carrito;
import Clase20_State.Estado;
import Clase20_State.Producto;

public class Vacio implements Estado {
    public Estado agregarProducto(Carrito carrito, Producto producto) {
        carrito.getProductos().add(producto);
        return new Cargando();
    }

    public Estado cancelarCarrito() {
        return this;
    }

    public Estado volver(Carrito carrito) {
        return this;
    }

    public Estado siguiente() {
        return new Cargando();
    }

    @Override
    public String toString() {
        return "Vacio";
    }
}

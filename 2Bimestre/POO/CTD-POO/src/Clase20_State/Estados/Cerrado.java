package Clase20_State.Estados;

import Clase20_State.Carrito;
import Clase20_State.Estado;
import Clase20_State.Producto;

public class Cerrado implements Estado {
    public Estado agregarProducto(Carrito carrito, Producto producto) {
        System.out.println("No se aceptan productos, el carrito ya está cerrado");
        return this;
    }

    public Estado cancelarCarrito() {
        return new Vacio();
    }

    public Estado volver(Carrito carrito) {
        return new Pagando();
    }

    public Estado siguiente() {
        return new Vacio();
    }

    @Override
    public String toString() {
        return "Cerrado";
    }
}

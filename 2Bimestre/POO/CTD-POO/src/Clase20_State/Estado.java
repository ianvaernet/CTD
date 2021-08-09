package Clase20_State;

public interface Estado {
    public Estado agregarProducto(Carrito carrito, Producto producto);

    public Estado cancelarCarrito();

    public Estado volver(Carrito carrito);

    public Estado siguiente();

    public String toString();
}

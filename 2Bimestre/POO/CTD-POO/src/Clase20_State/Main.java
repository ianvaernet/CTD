package Clase20_State;

public class Main {
    public static void main(String[] args) {
        Carrito carrito = new Carrito();
        System.out.println("Estado: " + carrito.getEstadoActual());
        System.out.println("Productos: " + carrito.getProductos());
        carrito.agregarProducto(new Producto("Coca Cola", 150.0));
        System.out.println("Estado: " + carrito.getEstadoActual());
        System.out.println("Productos: " + carrito.getProductos());
        carrito.agregarProducto(new Producto("Sprite", 160.0));
        System.out.println("Estado: " + carrito.getEstadoActual());
        System.out.println("Productos: " + carrito.getProductos());
        carrito.siguiente();
        System.out.println("Estado: " + carrito.getEstadoActual());
        System.out.println("Productos: " + carrito.getProductos());
        carrito.siguiente();
        carrito.agregarProducto(new Producto("Lays", 95.0));
        System.out.println("Estado: " + carrito.getEstadoActual());
        System.out.println("Productos: " + carrito.getProductos());
        carrito.volver();
        System.out.println("Estado: " + carrito.getEstadoActual());
        System.out.println("Productos: " + carrito.getProductos());
        carrito.cancelarCarrito();
        System.out.println("Estado: " + carrito.getEstadoActual());
        System.out.println("Productos: " + carrito.getProductos());
    }
}

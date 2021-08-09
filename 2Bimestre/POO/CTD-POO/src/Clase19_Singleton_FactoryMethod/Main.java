package Clase19_Singleton_FactoryMethod;

public class Main {
    public static void main(String[] args) {
        Almacen almacen = Almacen.getInstance();
        almacen.agregarProducto(ProductoFactory.crearProducto(ProductoFactory.CODIGO_CAJA));
        almacen.agregarProducto(ProductoFactory.crearProducto(ProductoFactory.CODIGO_PELOTA_FUTBOL));
        almacen.agregarProducto(ProductoFactory.crearProducto(ProductoFactory.CODIGO_PELOTA_TENIS));

        System.out.println(almacen.calcularEspacioNecesario());
    }
}

package Clase19_Singleton_FactoryMethod;

public class ProductoFactory {
    public static final String CODIGO_CAJA = "CAJA10X10";
    public static final String CODIGO_PELOTA_FUTBOL = "PELOTAFUTBOL";
    public static final String CODIGO_PELOTA_TENIS = "PELOTATENIS";

    public static Producto crearProducto(String codigoProducto) {
        switch (codigoProducto) {
            case CODIGO_CAJA:
                return new Caja(10.0, 10.0, 10.0, 10.0);
            case CODIGO_PELOTA_FUTBOL:
                return new Pelota(1.0, 11.0);
            case CODIGO_PELOTA_TENIS:
                return new Pelota(0.2, 0.32);
            default:
                return null;
        }
    }
}

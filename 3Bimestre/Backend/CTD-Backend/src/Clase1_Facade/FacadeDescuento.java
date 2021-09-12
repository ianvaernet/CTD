package Clase1_Facade;

public class FacadeDescuento {
    private ApiProducto apiProducto;
    private ApiTarjeta apiTarjeta;
    private ApiCantidad apiCantidad;

    public FacadeDescuento() {
        this.apiProducto = new ApiProducto();
        this.apiTarjeta = new ApiTarjeta();
        this.apiCantidad = new ApiCantidad();

    }

    public int descuento(Producto producto, Tarjeta tarjeta, int cantidad) {
        int descuento = apiProducto.descuento(producto);
        descuento += apiTarjeta.descuento(tarjeta);
        descuento += apiCantidad.descuento(cantidad);
        return descuento;
    }
}

package Clase1_Facade;

public class ApiTarjeta {
    public int descuento(Tarjeta tarjeta) {
        if (tarjeta.getBanco().equals("Star Bank")) return 20;
        return 0;
    }
}

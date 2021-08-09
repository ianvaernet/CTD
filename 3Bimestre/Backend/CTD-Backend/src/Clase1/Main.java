package Clase1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Tarjeta tarjetaSantander = new Tarjeta("Santander", "4329-8623-3278-9030");
        Tarjeta tarjetaStarBank = new Tarjeta("Star Bank", "4329-8623-3278-9030");
        ArrayList<Producto> carrito = new ArrayList<>();
        carrito.add(new Producto("Lentejas", "Lata"));
        carrito.add(new Producto("Zucaritas", "Cereal"));

        FacadeDescuento caja = new FacadeDescuento();
        System.out.println(caja.descuento(carrito.get(0), tarjetaSantander, 5));
        System.out.println(caja.descuento(carrito.get(0), tarjetaStarBank, 5));
        System.out.println(caja.descuento(carrito.get(0), tarjetaStarBank, 15));

        System.out.println(caja.descuento(carrito.get(1), tarjetaSantander, 5));
        System.out.println(caja.descuento(carrito.get(1), tarjetaStarBank, 5));
        System.out.println(caja.descuento(carrito.get(1), tarjetaStarBank, 15));
    }
}

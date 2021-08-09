package Clase11.Mesa;

public class Main {
    public static void main(String[] args) {
        Cuenta cajaDeAhorro = new CajaDeAhorro(0.0, 3.0);
        Cuenta cuentaCorriente = new CuentaCorriente(0.0, 1500.0);
        Cliente cliente1 = new Cliente(1, "Borelli", 12345678, 112345678, cajaDeAhorro);
        Cliente cliente2 = new Cliente(2, "Perez", 87654321, 287654321, cuentaCorriente);
        cliente1.getCuenta().depositarEfectivo(1000.0);
        cliente1.getCuenta().extraerEfectivo(500.0);
        System.out.println(cliente1.getCuenta().informarSaldo());
        cliente1.getCuenta().extraerEfectivo(700.0);

        cliente2.getCuenta().extraerEfectivo(1000.0);
        System.out.println(cliente2.getCuenta().informarSaldo());
        cliente2.getCuenta().extraerEfectivo(600.0);
    }
}

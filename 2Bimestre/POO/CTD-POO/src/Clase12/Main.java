package Clase12;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Cliente cliente = new Cliente(123,1234,1,"Perez");

        Cuenta cuenta = new CuentaCorriente(cliente,500.0,300.0);

        cuenta = new CuentaCajaDeAhorro(cliente,500.0,0.1);

        cuenta = new CuentaComitente(cliente, 1000.0, "asd123");
        cuenta.depositar(500.0);
        cuenta.extraer(300.0);
        cuenta.extraer(1200.0);
        ((CuentaComitente) cuenta).extraer(1200.0, "asd12");
        ((CuentaComitente) cuenta).extraer(1100.0, "asd123");

    }
}

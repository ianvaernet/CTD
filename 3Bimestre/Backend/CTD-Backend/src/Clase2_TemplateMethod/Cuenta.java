package Clase2_TemplateMethod;

public class Cuenta {
    private final int nroCuenta;
    private double saldo;

    public Cuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
        this.saldo = 0;
    }

    public void depositar(double importe) {
        this.saldo += importe;
    }
}

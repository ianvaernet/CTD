package Clase11.Mesa;

public abstract class Cuenta {
    private Double saldo;

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cuenta(Double saldo) {
        this.saldo = saldo;
    }
    public abstract void depositarEfectivo(Double valor);
    public abstract void extraerEfectivo(Double valor);
    public Double informarSaldo() {
        return this.saldo;
    }
}

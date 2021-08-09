package Clase11.Mesa;

public class CajaDeAhorro extends Cuenta {
    private Double tasaDeInteres;

    public Double getTasaDeInteres() {
        return tasaDeInteres;
    }

    public CajaDeAhorro(Double saldo, Double tasaDeInteres) {
        super(saldo);
        this.tasaDeInteres = tasaDeInteres;
    }
    @Override
    public void depositarEfectivo(Double valor) {
        setSaldo(getSaldo()+valor);
    }
    @Override
    public void extraerEfectivo(Double valor) {
        if (valor > getSaldo()) System.out.println("Saldo insuficiente");
        else setSaldo(getSaldo()-valor);
    }
    public void cobrarIntereses() {
        setSaldo(getSaldo() * getTasaDeInteres());
    }
}

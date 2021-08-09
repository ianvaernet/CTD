package Clase11.Mesa;

public class CuentaCorriente extends Cuenta {
    private Double montoGiro;

    public Double getMontoGiro() {
        return montoGiro;
    }

    public void setMontoGiro(Double montoGiro) {
        this.montoGiro = montoGiro;
    }

    public CuentaCorriente(Double saldo, Double montoGiro) {
        super(saldo);
        this.montoGiro = montoGiro;
    }
    @Override
    public void depositarEfectivo(Double valor) {
        setSaldo(getSaldo()+valor);
    }
    @Override
    public void extraerEfectivo(Double valor) {
        if (valor > getSaldo() + getMontoGiro()) System.out.println("Saldo insuficiente");
        else {
            if (valor > getSaldo()) {
                setSaldo(0.0);
                setMontoGiro(getMontoGiro() - (valor-getSaldo()) );
            }
            else setSaldo(getSaldo()-valor);
        }
    }
}

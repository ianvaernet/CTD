package Clase12;

public class CuentaComitente extends Cuenta {
    private String CNV;

    public CuentaComitente(Cliente cliente, Double saldo, String CNV) {
        super(cliente, saldo);
        this.CNV = CNV;
    }

    @Override
    public void depositar (Double monto) {
        Double montoDepositado = monto * 0.99;
        setSaldo(getSaldo() + montoDepositado);
        System.out.println("Se depositaron " + montoDepositado + " pesos");
        System.out.println("Tu nuevo saldo es de " + getSaldo());
    }

    @Override
    public void extraer (Double monto) {
        if (monto <= getSaldo() * 0.5) {
            setSaldo(getSaldo() - monto);
            System.out.println("Se retiraron " + monto + " pesos");
            System.out.println("Nuevo saldo: " + getSaldo());
        } else {
            System.out.println("Límite de extracción excedido");
        }
    }

    public void extraer (Double monto, String CNV) {
        if (CNV.equals(this.CNV)) {
            if (monto <= getSaldo()) {
                setSaldo(getSaldo() - monto);
                System.out.println("ser retiraron " + monto + " pesos");
                System.out.println("nuevo saldo: " + getSaldo());
            } else {
                System.out.println("Saldo insuficiente");
            }
        } else {
            System.out.println("CNV incorrecta");
        }
    }
}

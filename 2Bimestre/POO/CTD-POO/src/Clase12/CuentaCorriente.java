package Clase12;

public class CuentaCorriente extends Cuenta{

    private Double montoAutorizado;

    public CuentaCorriente(Cliente cliente, Double saldo, Double montoAutorizado) {
        super(cliente, saldo);
        this.montoAutorizado = montoAutorizado;
    }


    @Override
    public void extraer(Double monto) {
        if(monto <= getSaldo() + montoAutorizado){
            System.out.println("ser retiraron " + monto + " pesos");
            setSaldo(getSaldo() - monto);
            System.out.println("nuevo saldo: " + getSaldo());
        }else{
            System.out.println("saldo insuficiente");
        }
    }


}

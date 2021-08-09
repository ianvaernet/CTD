package Clase12;

public class CuentaCajaDeAhorro extends Cuenta{

    private Double tasaDeInteres;

    public CuentaCajaDeAhorro(Cliente cliente ,Double saldo, Double tasaDeInteres){
        super(cliente,saldo);
        this.tasaDeInteres = tasaDeInteres;
    }


    @Override
    public void extraer(Double monto) {
        if(getSaldo() >= monto){
            System.out.println("ser retiraron " + monto + " pesos");
            setSaldo(getSaldo() - monto);
            System.out.println("nuevo saldo: " + getSaldo());
        }else{
            System.out.println("saldo insuficiente");
        }
    }

    public void cobrarInteres(){
        System.out.println("cobraste interes");
        depositar(getSaldo() * tasaDeInteres);
    }
}

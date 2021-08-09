package Clase11.Mesa;

public class Cliente {
    private Integer numeroCliente;
    private String apellido;
    private Integer DNI;
    private Integer CUIT;
    private Cuenta cuenta;

    public Cuenta getCuenta() {
        return cuenta;
    }

    public Cliente(Integer numeroCliente, String apellido, Integer DNI, Integer CUIT, Cuenta cuenta) {
        this.numeroCliente = numeroCliente;
        this.apellido = apellido;
        this.DNI = DNI;
        this.CUIT = CUIT;
        this.cuenta = cuenta;
    }
}

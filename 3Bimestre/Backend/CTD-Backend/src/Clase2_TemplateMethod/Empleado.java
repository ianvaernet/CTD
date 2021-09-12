package Clase2_TemplateMethod;

public abstract class Empleado {
    private String nombre;
    private String apellido;
    private Cuenta cuenta;

    public Empleado(String nombre, String apellido, Cuenta cuenta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuenta = cuenta;
    }

    public final void liquidarSueldo() {
        double importe = calcularSueldo();
        generarRecibo(importe);
        depositar(importe);
    }

    protected abstract double calcularSueldo();
    protected void generarRecibo(double importe) {
        System.out.println("====== RECIBO DE SUELDO ======");
        System.out.println("Empleado: " + nombre + " " + apellido);
        System.out.println("Total: " + importe);
    }
    protected void depositar(double importe) {
        cuenta.depositar(importe);
        System.out.println("Se han depositado $" + importe + " en la cuenta de " + nombre + " " + apellido);
    }
}

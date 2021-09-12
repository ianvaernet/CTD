package Clase2_TemplateMethod;

public class EmpleadoContratado extends Empleado {
    private double sueldoBasico;
    private double descuentos;
    private double premios;

    public EmpleadoContratado(String nombre, String apellido, Cuenta cuenta, double sueldoBasico, double descuentos, double premios) {
        super(nombre, apellido, cuenta);
        this.sueldoBasico = sueldoBasico;
        this.descuentos = descuentos;
        this.premios = premios;
    }

    protected double calcularSueldo() {
        return sueldoBasico + premios - descuentos;
    }

    @Override
    protected void generarRecibo(double importe) {
        super.generarRecibo(importe);
        System.out.println("Sueldo básico: " + sueldoBasico);
        System.out.println("Descuentos: " + descuentos);
        System.out.println("Premios: " + premios);
    }
}

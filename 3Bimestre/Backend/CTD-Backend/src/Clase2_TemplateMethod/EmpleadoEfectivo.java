package Clase2_TemplateMethod;

public class EmpleadoEfectivo extends Empleado {
    private int cantidadHoras;
    private double valorPorHora;

    public EmpleadoEfectivo(String nombre, String apellido, Cuenta cuenta, int cantidadHoras, double valorPorHora) {
        super(nombre, apellido, cuenta);
        this.cantidadHoras = cantidadHoras;
        this.valorPorHora = valorPorHora;
    }

    protected double calcularSueldo() {
        return cantidadHoras * valorPorHora;
    }

    @Override
    protected void generarRecibo(double importe) {
        super.generarRecibo(importe);
        System.out.println("Cantidad de horas: " + cantidadHoras);
        System.out.println("Valor por hora: " + valorPorHora);
    }
}

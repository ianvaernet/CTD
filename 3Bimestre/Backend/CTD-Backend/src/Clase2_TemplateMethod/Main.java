package Clase2_TemplateMethod;

public class Main {
    public static void main(String[] args) {
        EmpleadoContratado empleado1 = new EmpleadoContratado("Ian", "Vaernet", new Cuenta(1), 70000.0, 1500.0, 5000.0);
        EmpleadoEfectivo empleado2 = new EmpleadoEfectivo("Erick", "Vaernet", new Cuenta(2), 160, 500);

        empleado1.liquidarSueldo();
        System.out.println();
        empleado2.liquidarSueldo();
    }
}

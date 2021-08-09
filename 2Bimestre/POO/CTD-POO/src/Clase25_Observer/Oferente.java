package Clase25_Observer;

import java.util.Scanner;

public class Oferente implements Observador {
    private String nombre;
    private String apellido;
    private Integer DNI;
    private Double montoTope;

    public Oferente(String nombre, String apellido, Integer DNI, Double montoTope) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.montoTope = montoTope;
    }

    public void ofertar(Subasta subasta) {
        System.out.println("Ingrese el valor a incrementar en la oferta: ");
        Scanner scanner = new Scanner(System.in);
        Double monto = scanner.nextDouble();
        if (subasta.getMonto() + monto <= montoTope) {
            System.out.println(nombre + " sube la oferta en " + monto);
            subasta.ofertar(nombre, subasta.getMonto() + monto);
        } else {
            System.out.println("Monto tope excedido.");
        }
    }

    public void actualizar(Subasta subasta) {
        if (montoTope > subasta.getMonto()) {
            System.out.println("El monto de la subasta es " + subasta.getMonto());
            System.out.println("¿Quiere ofertar " + nombre + "?");
            Scanner scanner = new Scanner(System.in);
            boolean vaAOfertar = scanner.nextBoolean();
            if (vaAOfertar) ofertar(subasta);
        }
    }
}

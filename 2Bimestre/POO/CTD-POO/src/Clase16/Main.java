package Clase16;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Puerto puerto = new Puerto("Buenos Aires");
        puerto.ingresarContenedor(new Contenedor(1, "EEUU", false));
        puerto.ingresarContenedor(new Contenedor(4, "Desconocida", true));
        puerto.ingresarContenedor(new Contenedor(3, "China", false));
        puerto.ingresarContenedor(new Contenedor(2, "Desconocida", false));
        puerto.ingresarContenedor(new Contenedor(6, "Desconocida", true));
        puerto.ingresarContenedor(new Contenedor(5, "Brasil", true));

        puerto.mostrarContenedores();
        System.out.println(puerto.calcularContenedoresPeligrosos());
    }
}

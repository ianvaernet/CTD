package Clase7_Flyweight;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArbolFactory arbolFactory = new ArbolFactory();
        ArrayList<ArbolPosicionado> arboles = new ArrayList<>();

        for (int x=1; x<=1000; x++) {
            for (int y=1; y<=500; y++) {
                arboles.add(arbolFactory.getArbol(200.0, 400.0, "verde", "ornamental", x, y));
            }
        }

        for (int x=1; x<=1000; x++) {
            for (int y=1; y<=500; y++) {
                arboles.add(arbolFactory.getArbol(500.0, 300.0, "rojo", "frutal", x, y));
            }
        }

        System.out.println("Cantidad de instancias de ArbolPosicionado: " + ArbolPosicionado.getContador());
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024) + " MB");
    }
}

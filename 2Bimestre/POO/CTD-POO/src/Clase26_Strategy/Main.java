package Clase26_Strategy;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Punto punto1 = new Punto(-54.36, 18.21);
        Punto punto2 = new Punto(5.88, 32.19);
        Punto punto3 = new Punto(30.75, -41.96);

        Recorrido recorrido12A = new Recorrido(punto1, punto2, new Automovil());
        Recorrido recorrido12B = new Recorrido(punto1, punto2, new Bicicleta());
        Recorrido recorrido12C = new Recorrido(punto1, punto2, new Caminando());

        Recorrido recorrido13A = new Recorrido(punto1, punto3, new Automovil());
        Recorrido recorrido13B = new Recorrido(punto1, punto3, new Bicicleta());
        Recorrido recorrido13C = new Recorrido(punto1, punto3, new Caminando());

        Recorrido recorrido23A = new Recorrido(punto2, punto3, new Automovil());
        Recorrido recorrido23B = new Recorrido(punto2, punto3, new Bicicleta());
        Recorrido recorrido23C = new Recorrido(punto2, punto3, new Caminando());

        ArrayList<Recorrido> recorridos = new ArrayList<>();
        recorridos.add(recorrido12A);
        recorridos.add(recorrido12B);
        recorridos.add(recorrido12C);
        recorridos.add(recorrido13A);
        recorridos.add(recorrido13B);
        recorridos.add(recorrido13C);
        recorridos.add(recorrido23A);
        recorridos.add(recorrido23B);
        recorridos.add(recorrido23C);

        recorridos.forEach(recorrido -> System.out.println(recorrido.calcularTiempo()));
    }
}

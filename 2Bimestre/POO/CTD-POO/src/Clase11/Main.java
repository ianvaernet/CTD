package Clase11;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>(Arrays.asList(new Perro("Domi", "Caniche"), new Gato("Cati", 25, 2.75)));
        mascotas.forEach(Mascota::hacerRuido);
    }

}

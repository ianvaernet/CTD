package Clase3;
import java.util.ArrayList;
import java.util.Scanner;

// https://drive.google.com/file/d/1GkBHEvUC5sqdyi10VidtQulEOosy61XJ/view
public class Main {
    public static void main(String[] args) {
        ejercicio3();
    }

    public static void ejercicio1y2() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del jugador 1: ");
        String name1 = scanner.nextLine();
        System.out.print("Nombre del jugador 2: ");
        String name2 = scanner.nextLine();

        System.out.print(name1+" - Introduza su jugada: ");
        String jugada1 = scanner.nextLine();
        while (!jugada1.equals("*")) {
            System.out.print(name2+" - Introduza su jugada: ");
            String jugada2 = scanner.nextLine();

            Integer ganador = cualGana(jugada1, jugada2);
            switch (ganador) {
                case 0:
                    System.out.println("Empate");
                    break;
                case 1:
                    System.out.println("Ganó "+name1);
                    break;
                case 2:
                    System.out.println("Ganó "+name2);
                    break;
            }
            System.out.print("Jugador 1 - Introduza su jugada: ");
            jugada1 = scanner.nextLine();
        }

    }

    public static int cualGana(String jugada1, String jugada2) {
        if (jugada1.toLowerCase().equals(jugada2.toLowerCase())) return 0;
        if (
                (jugada1.toLowerCase().equals("piedra") && jugada2.toLowerCase().equals("tijera"))
                        || (jugada1.toLowerCase().equals("tijera") && jugada2.toLowerCase().equals("papel"))
                        || (jugada1.toLowerCase().equals("papel") && jugada2.toLowerCase().equals("piedra"))
                        || (jugada1.toLowerCase().equals("spock") && !jugada2.toLowerCase().equals("papel"))
        ) return 1;
        return 2;
    }


    public static void ejercicio3() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número: ");
        Integer n = scanner.nextInt();
        ArrayList<Integer> numerosPrimos = new ArrayList<Integer>();
        Integer numero = 2; // Empieza en 2 porque por convención 1 no se considera primo
        while (numerosPrimos.size() < n) {
            while (!esPrimo(numero)) {
                numero++;
            }
            numerosPrimos.add(numero);
            numero++; // Se incrementa en 1 ya que sino la próxima vez va a probar con el mismo número, que es primo, dando el mismo resultado
        }
        System.out.println("Los "+n+" primeros números primos son "+numerosPrimos);
    }
    static boolean esDivisible(int numero, int divisor) {
        return numero % divisor == 0;
    }

    static boolean esPrimo(int numero) {
        for (int divisor = 2; divisor < numero; divisor++) {
            if (esDivisible(numero, divisor)) return false;
        }
        return true;
    }

}

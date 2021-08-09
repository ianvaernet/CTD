package com.company;

public class Main {

    public static void main(String[] args) {
        Policia policia = new Policia("Ian", "Vaernet", 22716);

        ArmaCorta armaCorta1 = new ArmaCorta(10, 70.5, "Glock", 9.0, EstadoArma.Nueva, policia, true);
        ArmaCorta armaCorta2 = new ArmaCorta(15,205.0, "Beretta", 14.0, EstadoArma.EnUso, policia, false);

        ArmaLarga armaLarga1 = new ArmaLarga(35, 180.0, "Glock", 7.0, EstadoArma.EnUso, policia, true, "Una descripción de uso", 2);
        ArmaLarga armaLarga2 = new ArmaLarga(7, 430.0, "Beretta", 14.0, EstadoArma.EnMantenimiento, policia, true, "Otra descripción de uso", 3);
        ArmaLarga armaLarga3 = new ArmaLarga(14, 310.0, "Walther", 14.0, EstadoArma.Nueva, policia, false, "Otra descripción de uso", 3);

        System.out.println("ARMA CORTA 1\n" + armaCorta1);
        System.out.println("\nARMA CORTA 2\n" + armaCorta1);
        System.out.println("\nARMA LARGA 1\n" + armaLarga1);
        System.out.println("\nARMA LARGA 2\n" + armaLarga2);
        System.out.println("\nARMA LARGA 3\n" + armaLarga3);

        System.out.println("\nARMA CORTA 1 tiene mediano alcance: " + armaCorta1.tieneMedianoAlcance());
        System.out.println("ARMA CORTA 2 tiene mediano alcance: " + armaCorta2.tieneMedianoAlcance());

        System.out.println("\nARMA LARGA 1 comparada con ARMA LARGA 2: " + armaLarga1.compareTo(armaLarga2));
        System.out.println("ARMA LARGA 2 comparada con ARMA LARGA 3: " + armaLarga2.compareTo(armaLarga3));
        System.out.println("ARMA LARGA 3 comparada con ARMA LARGA 1: " + armaLarga3.compareTo(armaLarga1));
    }
}

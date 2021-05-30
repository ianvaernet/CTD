package com.ctd;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Edad: ");
        int age = scanner.nextInt();
        Persona persona = new Persona(name, age);
        System.out.printf("Hola %s de %d años", persona.getName(), persona.getAge());
    }
}

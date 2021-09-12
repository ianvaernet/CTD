package Clase7_Flyweight;

public class Arbol {
    static int contador;
    private Double alto;
    private Double ancho;
    private String color;
    private String tipo;

    public Arbol(Double alto, Double ancho, String color, String tipo) {
        this.alto = alto;
        this.ancho = ancho;
        this.color = color;
        this.tipo = tipo;
        contador++;
        System.out.println("Cantidad de instancias de Arbol: " + contador);
    }


}

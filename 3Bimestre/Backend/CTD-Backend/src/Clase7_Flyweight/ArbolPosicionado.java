package Clase7_Flyweight;

public class ArbolPosicionado {
    private static int contador;
    private Arbol arbol;
    private Integer x;
    private Integer y;

    public ArbolPosicionado(Arbol arbol, Integer x, Integer y) {
        this.arbol = arbol;
        this.x = x;
        this.y = y;
        contador++;
    }

    public static int getContador() {
        return contador;
    }
}

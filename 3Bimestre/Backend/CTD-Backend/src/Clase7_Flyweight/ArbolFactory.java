package Clase7_Flyweight;

import java.util.HashMap;
import java.util.Map;

public class ArbolFactory {
    private Map<String, Arbol> arboles;

    public ArbolFactory() {
        this.arboles = new HashMap<>();
    }

    public ArbolPosicionado getArbol(Double alto, Double ancho, String color, String tipo, Integer x, Integer y) {
        String clave = alto.toString() + ancho.toString() + color + tipo;

        if (arboles.get(clave) == null) {
            arboles.put(clave, new Arbol(alto, ancho, color, tipo));
        }
        Arbol arbol = arboles.get(clave);
        return new ArbolPosicionado(arbol, x, y);
    }
}

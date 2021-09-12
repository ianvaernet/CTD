package Clase22_Composite;

import java.util.ArrayList;
import java.util.List;

public class Tren implements Geometrico {
    private List<Geometrico> partes;

    public Tren() {
        this.partes = new ArrayList<Geometrico>();
    }

    public void agregarParte(Geometrico parte) {
        this.partes.add(parte);
    }

    public List<Geometrico> getPartes() {
        return partes;
    }

    public Double calcularArea() {
        Double acumulador = 0.0;
        for (Geometrico parte : partes) {
            acumulador += parte.calcularArea();
        }
        return acumulador;
    }
}

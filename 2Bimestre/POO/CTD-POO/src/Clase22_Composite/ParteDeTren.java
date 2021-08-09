package Clase22_Composite;

import java.util.ArrayList;
import java.util.List;

public class ParteDeTren implements Geometrico {
    private List<Geometrico> figuras;

    public ParteDeTren() {
        this.figuras = new ArrayList<Geometrico>();
    }

    public void agregarFigura(Geometrico figura) {
        this.figuras.add(figura);
    }

    public void removerFigura(Geometrico figura) {
        this.figuras.remove(figura);
    }

    @Override
    public Double calcularArea() {
        Double acumulador = 0.0;
        for (Geometrico figura : figuras) {
            acumulador += figura.calcularArea();
        }
        return acumulador;
    }
}

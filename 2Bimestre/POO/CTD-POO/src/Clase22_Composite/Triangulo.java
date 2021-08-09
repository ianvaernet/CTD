package Clase22_Composite;

public class Triangulo implements Geometrico {
    private Double base;
    private Double altura;

    public Triangulo(Double base, Double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public Double calcularArea() {
        return base * altura / 2;
    }
}

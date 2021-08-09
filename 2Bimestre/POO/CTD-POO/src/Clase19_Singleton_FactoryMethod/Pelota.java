package Clase19_Singleton_FactoryMethod;

public class Pelota extends Producto {
    private Double radio;

    public Pelota(Double peso, Double radio) {
        super(peso);
        this.radio = radio;
    }

    public Double calcularEspacio() {
        return 4 / 3 * Math.PI * Math.pow(radio, 3);
    }
}

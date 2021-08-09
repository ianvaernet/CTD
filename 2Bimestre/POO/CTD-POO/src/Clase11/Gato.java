package Clase11;

public class Gato extends Mascota {
    private Double peso;

    public Gato(String nombre, Integer energia, Double peso) {
        super(nombre, energia);
        this.peso = peso;
    }
    @Override
    public void hacerRuido() {
        System.out.println("Miau");
    }
}

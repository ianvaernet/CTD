package Clase11;

public class Perro extends Mascota {
    private String raza;

    public Perro(String nombre, String raza) {
        super(nombre, 50);
        this.raza = raza;
    }
    @Override
    public void hacerRuido() {
        System.out.println("Guau guau");
    }
}

package Clase11;

public abstract class Mascota {
    private String nombre;
    private Integer energia;

    public Mascota(String nombre, Integer energia) {
        this.nombre = nombre;
        this.energia = energia;
    }
    public abstract void hacerRuido();
    public void comer(Integer valor) {
        this.energia += valor;
    }
}

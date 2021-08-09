package Clase13;

public class Bombardero extends SistemaArmas implements Luchador,Volador{
    public Bombardero(Integer energia) {
        super(energia);
    }

    @Override
    public void atacar() {
        System.out.println("Ataque");
    }

    @Override
    public void defender() {
        System.out.println("Defensa");
    }

    @Override
    public void volar() {
        System.out.println("Vuelo");
    }
}

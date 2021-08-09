package Clase13;

public class Tanque extends SistemaArmas implements Luchador{
    public Tanque(Integer energia) {
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

}

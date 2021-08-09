package Clase13;

public class RobotPesado extends SistemaArmas implements Luchador,Volador{
    public RobotPesado(Integer energia) {
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

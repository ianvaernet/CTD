package Clase13;

public abstract class SistemaArmas {
    private Integer energia;

    public SistemaArmas(Integer energia) {
        this.energia = energia;
    }

    public void mostrar() {
        System.out.println("Me muestro");
    };
}

package Clase10;

public class Nave extends Objeto {
    private double velocidad;
    private int vida;

    public Nave(int x, int y, char direccion, double velocidad, int vida) {
        super(x, y, direccion);
        this.velocidad = velocidad;
        this.vida = vida;
    }

    @Override
    public void irA(int x, int y, char direccion) {
        if(this.getDireccion() == direccion) {
            this.setPosx(x);
            this.setPosy(y);
        }
    }
    public int restarVidas(int valor) {
        int vidas = this.getVida() - valor;
        this.setVida(vidas);
        return vidas;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}

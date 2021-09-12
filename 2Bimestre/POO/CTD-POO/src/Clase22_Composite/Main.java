package Clase22_Composite;

public class Main {
    public static void main(String[] args) {
        Tren tren = new Tren();
        ParteDeTren vagon = new ParteDeTren();
        vagon.agregarFigura(new Rectangulo(5.0, 4.0));
        vagon.agregarFigura(new Circulo(1.0));
        vagon.agregarFigura(new Circulo(1.0));
        vagon.agregarFigura(new Circulo(1.0));
        System.out.println(vagon.calcularArea());

        ParteDeTren locomotora = new ParteDeTren();
        locomotora.agregarFigura(new Rectangulo(6.0,4.0));
        locomotora.agregarFigura(new Circulo(1.0));
        locomotora.agregarFigura(new Circulo(1.0));
        locomotora.agregarFigura(new Triangulo(2.0, 2.0));
        System.out.println(locomotora.calcularArea());

        tren.agregarParte(vagon);
        tren.agregarParte(locomotora);
        System.out.println(tren.calcularArea());
    }
}

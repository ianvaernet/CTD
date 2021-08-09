package Clase22_Composite;

public class Main {
    public static void main(String[] args) {
        Tren tren = new Tren();
        Geometrico rectangulo1 = new Rectangulo(5.0, 4.0);
        Geometrico circulo1 = new Circulo(1.0);
        Geometrico circulo2 = new Circulo(1.0);
        Geometrico circulo3 = new Circulo(1.0);
        ParteDeTren vagon = new ParteDeTren();
        vagon.agregarFigura(rectangulo1);
        vagon.agregarFigura(circulo1);
        vagon.agregarFigura(circulo2);
        vagon.agregarFigura(circulo3);
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

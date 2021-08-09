package Clase25_Observer;

public class Main {
    public static void main(String[] args) {
        Subasta subasta = new Subasta(100.0, "Una casa");
        Oferente nayla = new Oferente("Nayla", "Saez", 37608658, 5000.0);
        Oferente mar = new Oferente("Mar", "Opradolce", 39558146, 3000.0);
        Oferente thairy = new Oferente("Thairy", "Daza", 95942052, 4000.0);
        subasta.agregar(nayla);
        subasta.agregar(mar);
        subasta.agregar(thairy);
        subasta.notificar();
    }
}

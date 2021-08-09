package Clase25_Observer;

import java.util.ArrayList;
import java.util.List;

public class Subasta implements Observable {
    private List<Observador> oferentes;
    private Double monto;
    private String descripcion;
    private String ganadorActual;

    public Subasta(Double monto, String descripcion) {
        this.monto = monto;
        this.descripcion = descripcion;
        this.oferentes = new ArrayList<>();
    }

    public Double getMonto() {
        return monto;
    }

    @Override
    public void agregar(Observador observador) {
        oferentes.add(observador);
    }

    @Override
    public void quitar(Observador observador) {
        oferentes.remove(observador);
    }

    @Override
    public void notificar() {
        Double monto = this.monto;
        for (Observador oferente: oferentes) {
            oferente.actualizar(this);
        }
        if (this.monto > monto) notificar();
        else System.out.println(ganadorActual + " ganó la subasta");
    }

    public void ofertar(String nombreOferente, Double monto) {
        this.monto = monto;
        ganadorActual = nombreOferente;
    }
}

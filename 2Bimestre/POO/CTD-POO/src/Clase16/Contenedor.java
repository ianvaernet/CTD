package Clase16;

public class Contenedor implements Comparable<Contenedor> {
    private Integer id;
    private String pais;
    private Boolean materialesPeligrosos;

    public Contenedor(Integer id, String pais, Boolean materialesPeligrosos) {
        this.id = id;
        this.pais = pais;
        this.materialesPeligrosos = materialesPeligrosos;
    }
    public int compareTo(Contenedor contenedor) {
        return this.id.compareTo(contenedor.id);
    }
    public Boolean getMaterialesPeligrosos() {
        return materialesPeligrosos;
    }
    public String getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", pais: "+pais + ", materialesPeligrosos: " + materialesPeligrosos;
    }
}

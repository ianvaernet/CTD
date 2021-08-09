package Clase16;

import java.util.ArrayList;
import java.util.List;

public class Puerto {
    private List<Contenedor> contenedores;
    private String nombre;

    public Puerto(String nombre) {
        this.contenedores = new ArrayList<>();
        this.nombre = nombre;
    }
    public void ingresarContenedor(Contenedor contenedor) {
        contenedores.add(contenedor);
    }
    public void mostrarContenedores() {
        List<Contenedor> contenedoresOrdenados = new ArrayList<>(this.contenedores);
        contenedoresOrdenados.sort(null);
        for (Contenedor contenedor : contenedoresOrdenados) {
            System.out.println(contenedor);
        }
    }
    public Integer calcularContenedoresPeligrosos() {
        Integer contador = 0;
        for (Contenedor contenedor : this.contenedores) {
            if (contenedor.getMaterialesPeligrosos() && contenedor.getPais().equals("Desconocida") ) contador++;
        }
        return contador;
    }
}

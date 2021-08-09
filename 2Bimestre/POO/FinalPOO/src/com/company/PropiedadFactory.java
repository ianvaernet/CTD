package com.company;

public class PropiedadFactory {
    private static PropiedadFactory instance;
    public static final String CASA_SAN_MARTIN = "CASA_SAN_MARTIN";
    public static final String CASA_MITRE = "CASA_MITRE";
    public static final String BARRIO_CERRADO_GUTIERRES = "BARRIO_CERRADO_GUTIERRES";

    private PropiedadFactory() {}
    public static PropiedadFactory getInstance() {
        if (instance == null) {
            instance = new PropiedadFactory();
        }
        return instance;
    }
    public Propiedad crearPropiedad(String codigoPropiedad) throws RuntimeException {
        switch (codigoPropiedad) {
            case CASA_SAN_MARTIN: return new Casa("Av. San Martín", 130, 500.0);
            case CASA_MITRE: return new Casa("Mitre", 233, 700.0);
            case BARRIO_CERRADO_GUTIERRES:
                BarrioCerrado barrioGutierres = new BarrioCerrado("Gutierres", 330, 2);
                barrioGutierres.agregarPropiedad(crearPropiedad(CASA_SAN_MARTIN));
                barrioGutierres.agregarPropiedad(crearPropiedad(CASA_MITRE));
                return barrioGutierres;
            default: throw new RuntimeException("Código de propiedad inválido");
        }
    }
}

package Clase10_Logs;

import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            int result = 1/0;
        } catch (Exception e) {
            logger.error("Error al intentar dividir por 0", e);
        }
    }
}

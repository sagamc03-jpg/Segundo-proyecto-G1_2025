package Servicios;

public class UtilServicios {

    public static double leerReal(String texto) {
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("¡Datos incompletos!");
        }
        try {
            return Double.parseDouble(texto);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Valor inválido");
        }
    }

}
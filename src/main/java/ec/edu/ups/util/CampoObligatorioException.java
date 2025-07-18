package ec.edu.ups.util;

/**
 * Excepción personalizada que se lanza cuando un campo obligatorio no ha sido proporcionado.
 */
public class CampoObligatorioException extends Exception {

    /**
     * Constructor que permite especificar un mensaje de error.
     *
     * @param mensaje Mensaje que describe la causa de la excepción.
     */
    public CampoObligatorioException(String mensaje) {
        super(mensaje);
    }
}

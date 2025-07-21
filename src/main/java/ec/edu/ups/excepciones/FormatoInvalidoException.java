package ec.edu.ups.excepciones;

/**
 * Excepción personalizada utilizada para indicar que un formato de entrada es inválido.
 * <p>
 * Por ejemplo, puede lanzarse cuando el formato de una cédula, correo electrónico u otro dato
 * no cumple con los requisitos esperados del sistema.
 */
public class FormatoInvalidoException extends Exception {

    /**
     * Crea una nueva instancia de {@code FormatoInvalidoException} con el mensaje especificado.
     *
     * @param mensaje Descripción del error de formato.
     */
    public FormatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}

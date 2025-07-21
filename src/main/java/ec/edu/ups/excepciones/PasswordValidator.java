package ec.edu.ups.excepciones;

/**
 * Clase utilitaria para validar contraseñas con un patrón específico.
 * <p>
 * Reglas de validación:
 * <ul>
 *   <li>Debe contener al menos una letra minúscula.</li>
 *   <li>Debe contener al menos una letra mayúscula.</li>
 *   <li>Debe contener al menos uno de los siguientes caracteres especiales: @, _ o -.</li>
 *   <li>Debe tener una longitud mínima de 6 caracteres.</li>
 * </ul>
 */
public class PasswordValidator {

    /**
     * Valida si una contraseña cumple con el patrón requerido.
     *
     * @param password La contraseña a validar.
     * @return {@code true} si la contraseña es válida; {@code false} en caso contrario.
     */
    public static boolean validar(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[@_-]).{6,}$");
    }
}

package ec.edu.ups.modelo;

/**
 * Enumeración que define los posibles roles de un usuario dentro del sistema.
 * <p>
 * Los roles disponibles son:
 * <ul>
 *     <li>{@code ADMINISTRADOR}: Tiene acceso completo al sistema y puede gestionar usuarios y productos.</li>
 *     <li>{@code USUARIO}: Tiene acceso limitado, generalmente para realizar compras o consultar productos.</li>
 * </ul>
 */
public enum Rol {
    ADMINISTRADOR,
    USUARIO
}

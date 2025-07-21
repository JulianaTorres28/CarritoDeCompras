package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que representa un usuario del sistema.
 * <p>
 * Cada usuario tiene credenciales de acceso, un rol que define sus permisos
 * y un conjunto de preguntas de seguridad asociadas para recuperación de cuenta.
 * </p>
 *
 * <p>Implementa {@link Serializable} para permitir persistencia en archivos o transmisión por red.</p>
 *
 */
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Nombre de usuario único utilizado para autenticación. */
    private String username;

    /** Contraseña del usuario. */
    private String contrasenia;

    /** Cédula de identidad del usuario. */
    private String cedula;

    /** Rol asignado al usuario (ADMIN, CLIENTE, etc.). */
    private Rol rol;

    /** Lista de preguntas de seguridad utilizadas para recuperación de cuenta. */
    private List<PreguntaSeguridad> preguntasSeguridad;

    /**
     * Constructor por defecto.
     * <p>Requerido para serialización, frameworks o instanciación sin datos iniciales.</p>
     */
    public Usuario() {
    }

    /**
     * Constructor que inicializa un usuario con sus datos básicos.
     *
     * @param nombreDeUsuario Nombre de usuario único.
     * @param contrasenia     Contraseña segura.
     * @param cedula          Cédula de identidad.
     * @param rol             Rol asignado dentro del sistema.
     */
    public Usuario(String nombreDeUsuario, String contrasenia, String cedula, Rol rol) {
        this.username = nombreDeUsuario;
        this.contrasenia = contrasenia;
        this.cedula = cedula;
        this.rol = rol;
    }

    /**
     * Devuelve la lista de preguntas de seguridad.
     *
     * @return lista de preguntas con sus respuestas.
     */
    public List<PreguntaSeguridad> getPreguntasSeguridad() {
        return preguntasSeguridad;
    }

    /**
     * Establece la lista de preguntas de seguridad.
     *
     * @param preguntasSeguridad lista de preguntas/respuestas asociadas.
     */
    public void setPreguntasSeguridad(List<PreguntaSeguridad> preguntasSeguridad) {
        this.preguntasSeguridad = preguntasSeguridad;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return username del usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username nuevo nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return contraseña.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasenia nueva contraseña.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return rol del usuario.
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol nuevo rol.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Devuelve la cédula del usuario.
     *
     * @return cédula.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula del usuario.
     *
     * @param cedula nueva cédula.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Representación textual del objeto Usuario.
     *
     * @return cadena con información relevante del usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", cedula='" + cedula + '\'' +
                ", rol=" + rol +
                ", preguntasSeguridad=" + preguntasSeguridad +
                '}';
    }
}

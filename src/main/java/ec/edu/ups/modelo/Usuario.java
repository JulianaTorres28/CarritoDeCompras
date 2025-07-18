package ec.edu.ups.modelo;

import java.util.List;

/**
 * Clase que representa un usuario del sistema, con sus credenciales, rol y preguntas de seguridad asociadas.
 */
public class Usuario {

    /** Nombre de usuario (único) utilizado para autenticación. */
    private String username;

    /** Contraseña del usuario. */
    private String contrasenia;

    /** Cédula de identidad del usuario. */
    private String cedula;

    /** Rol que define los permisos del usuario dentro del sistema. */
    private Rol rol;

    /** Lista de preguntas de seguridad asociadas al usuario. */
    private List<PreguntaSeguridad> preguntasSeguridad;

    /**
     * Constructor por defecto.
     */
    public Usuario() {
    }

    /**
     * Constructor que inicializa los campos principales del usuario.
     *
     * @param nombreDeUsuario Nombre de usuario.
     * @param contrasenia     Contraseña.
     * @param cedula          Cédula de identidad.
     * @param rol             Rol asignado al usuario.
     */
    public Usuario(String nombreDeUsuario, String contrasenia, String cedula, Rol rol) {
        this.username = nombreDeUsuario;
        this.contrasenia = contrasenia;
        this.cedula = cedula;
        this.rol = rol;
    }

    /**
     * Obtiene la lista de preguntas de seguridad.
     *
     * @return Lista de preguntas de seguridad.
     */
    public List<PreguntaSeguridad> getPreguntasSeguridad() {
        return preguntasSeguridad;
    }

    /**
     * Establece la lista de preguntas de seguridad.
     *
     * @param preguntasSeguridad Lista de preguntas.
     */
    public void setPreguntasSeguridad(List<PreguntaSeguridad> preguntasSeguridad) {
        this.preguntasSeguridad = preguntasSeguridad;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return Nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username Nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasenia Contraseña.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene el rol asignado al usuario.
     *
     * @return Rol.
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol Rol.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Obtiene la cédula del usuario.
     *
     * @return Cédula.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula del usuario.
     *
     * @param cedula Cédula.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Devuelve una representación en texto del objeto Usuario.
     *
     * @return Cadena con los datos del usuario.
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

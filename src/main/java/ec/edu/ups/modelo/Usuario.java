package ec.edu.ups.modelo;

import java.util.List;

public class Usuario {
    private String username;
    private String contrasenia;
    private Rol rol;
    private List<PreguntaSeguridad> preguntasSeguridad;

    public Usuario() {

    }

    public Usuario(String nombreDeUsuario, String contrasenia, Rol rol) {
        this.username = nombreDeUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public List<PreguntaSeguridad> getPreguntasSeguridad() {
        return preguntasSeguridad;
    }

    public void setPreguntasSeguridad(List<PreguntaSeguridad> preguntasSeguridad) {
        this.preguntasSeguridad = preguntasSeguridad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreDeUsuario='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                '}';
    }
}
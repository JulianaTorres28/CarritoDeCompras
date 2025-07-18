package ec.edu.ups.modelo;

/**
 * Clase que representa una pregunta de seguridad junto con su respuesta.
 * Estas preguntas son utilizadas como mecanismo de verificación para recuperación de cuenta.
 */
public class PreguntaSeguridad {

    private String pregunta;
    private String respuesta;

    /**
     * Constructor que inicializa la pregunta de seguridad con su respuesta.
     *
     * @param pregunta la pregunta de seguridad (por ejemplo, "¿Nombre de tu mascota?")
     * @param respuesta la respuesta correspondiente a la pregunta
     */
    public PreguntaSeguridad(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    /**
     * Obtiene la pregunta de seguridad.
     *
     * @return la pregunta
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Obtiene la respuesta a la pregunta de seguridad.
     *
     * @return la respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }
}

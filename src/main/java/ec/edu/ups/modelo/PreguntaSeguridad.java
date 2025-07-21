package ec.edu.ups.modelo;

import java.io.Serializable;

/**
 * Clase que representa una pregunta de seguridad junto con su respuesta.
 * <p>
 * Estas preguntas son utilizadas como mecanismo de verificación de identidad,
 * especialmente en procesos de recuperación de cuenta o autenticación secundaria.
 * </p>
 *
 * <p>Implementa {@link Serializable} para permitir su almacenamiento en archivos binarios.</p>
 *
 */
public class PreguntaSeguridad implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Enunciado de la pregunta de seguridad. */
    private String pregunta;

    /** Respuesta proporcionada por el usuario para esta pregunta. */
    private String respuesta;

    /**
     * Constructor que inicializa la pregunta de seguridad con su respectiva respuesta.
     *
     * @param pregunta enunciado de la pregunta (por ejemplo, "¿Nombre de tu primera mascota?")
     * @param respuesta respuesta correspondiente a la pregunta planteada
     */
    public PreguntaSeguridad(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    /**
     * Obtiene la pregunta de seguridad.
     *
     * @return una cadena con la pregunta
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Obtiene la respuesta asociada a la pregunta de seguridad.
     *
     * @return una cadena con la respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }
}

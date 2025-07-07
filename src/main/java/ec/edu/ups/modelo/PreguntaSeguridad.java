package ec.edu.ups.modelo;

public class PreguntaSeguridad {
    private String pregunta;
    private String respuesta;

    public PreguntaSeguridad(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

}

package ec.edu.ups.dao;

import ec.edu.ups.modelo.PreguntaSeguridad;
import java.util.List;

public interface PreguntaSeguridadDAO {
    List<PreguntaSeguridad> obtenerPreguntasBase();
    void guardarPreguntasPorUsuario(String username, List<PreguntaSeguridad> preguntas);
    boolean usuarioExiste(String username);
    List<PreguntaSeguridad> obtenerPreguntasAleatorias(String username, int cantidad);
}

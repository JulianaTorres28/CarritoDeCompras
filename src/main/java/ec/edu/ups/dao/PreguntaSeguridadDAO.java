package ec.edu.ups.dao;

import ec.edu.ups.modelo.PreguntaSeguridad;
import java.util.List;

/**
 * Interfaz que define las operaciones para gestionar preguntas de seguridad
 * asociadas a los usuarios del sistema.
 */
public interface PreguntaSeguridadDAO {

    /**
     * Obtiene la lista base de preguntas de seguridad disponibles.
     *
     * @return Lista de preguntas de seguridad predeterminadas.
     */
    List<PreguntaSeguridad> obtenerPreguntasBase();

    /**
     * Guarda las preguntas de seguridad y sus respuestas asociadas a un usuario específico.
     *
     * @param username  Nombre de usuario al que se asociarán las preguntas.
     * @param preguntas Lista de preguntas con sus respectivas respuestas.
     */
    void guardarPreguntasPorUsuario(String username, List<PreguntaSeguridad> preguntas);

    /**
     * Verifica si un usuario ya tiene preguntas de seguridad registradas.
     *
     * @param username Nombre de usuario a verificar.
     * @return true si el usuario tiene preguntas guardadas, false en caso contrario.
     */
    boolean usuarioExiste(String username);

    /**
     * Obtiene una cantidad aleatoria de preguntas de seguridad previamente registradas
     * para el usuario.
     *
     * @param username Nombre de usuario.
     * @param cantidad Número de preguntas a recuperar.
     * @return Lista de preguntas seleccionadas aleatoriamente.
     */
    List<PreguntaSeguridad> obtenerPreguntasAleatorias(String username, int cantidad);
}

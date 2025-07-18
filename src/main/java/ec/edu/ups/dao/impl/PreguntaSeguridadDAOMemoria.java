package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaSeguridadDAO;
import ec.edu.ups.modelo.PreguntaSeguridad;

import java.util.*;

/**
 * Implementación en memoria de la interfaz PreguntaSeguridadDAO.
 * Almacena preguntas de seguridad asociadas a usuarios sin utilizar base de datos.
 */
public class PreguntaSeguridadDAOMemoria implements PreguntaSeguridadDAO {

    private final List<PreguntaSeguridad> preguntasBase;
    private final Map<String, List<PreguntaSeguridad>> preguntasPorUsuario;
    private final Set<String> usuariosRegistrados;

    /**
     * Constructor que inicializa las preguntas base y estructuras de almacenamiento en memoria.
     */
    public PreguntaSeguridadDAOMemoria() {
        preguntasBase = new ArrayList<>();
        preguntasPorUsuario = new HashMap<>();
        usuariosRegistrados = new HashSet<>();

        preguntasBase.add(new PreguntaSeguridad("pregunta.mascota", ""));
        preguntasBase.add(new PreguntaSeguridad("pregunta.ciudad", ""));
        preguntasBase.add(new PreguntaSeguridad("pregunta.comida", ""));
        preguntasBase.add(new PreguntaSeguridad("pregunta.madre", ""));
        preguntasBase.add(new PreguntaSeguridad("pregunta.abuela", ""));
        preguntasBase.add(new PreguntaSeguridad("pregunta.cancion", ""));
        preguntasBase.add(new PreguntaSeguridad("pregunta.color", ""));
        preguntasBase.add(new PreguntaSeguridad("pregunta.deporte", ""));
        preguntasBase.add(new PreguntaSeguridad("pregunta.maestro", ""));
        preguntasBase.add(new PreguntaSeguridad("pregunta.pelicula", ""));
    }

    /**
     * Retorna la lista de preguntas base para la selección por parte del usuario.
     *
     * @return Lista de objetos PreguntaSeguridad con enunciados base.
     */
    @Override
    public List<PreguntaSeguridad> obtenerPreguntasBase() {
        return preguntasBase;
    }

    /**
     * Asocia una lista de preguntas de seguridad a un usuario específico.
     *
     * @param username  Nombre de usuario.
     * @param preguntas Lista de preguntas con sus respectivas respuestas.
     */
    @Override
    public void guardarPreguntasPorUsuario(String username, List<PreguntaSeguridad> preguntas) {
        usuariosRegistrados.add(username);
        preguntasPorUsuario.put(username, preguntas);
    }

    /**
     * Verifica si un usuario tiene preguntas de seguridad registradas.
     *
     * @param username Nombre de usuario.
     * @return true si el usuario está registrado, false si no.
     */
    @Override
    public boolean usuarioExiste(String username) {
        return preguntasPorUsuario.containsKey(username);
    }

    /**
     * Devuelve una cantidad determinada de preguntas aleatorias registradas por el usuario.
     *
     * @param username Nombre de usuario.
     * @param cantidad Número de preguntas a obtener.
     * @return Lista de preguntas aleatorias del usuario.
     */
    @Override
    public List<PreguntaSeguridad> obtenerPreguntasAleatorias(String username, int cantidad) {
        List<PreguntaSeguridad> preguntasUsuario = preguntasPorUsuario.get(username);
        if (preguntasUsuario == null || preguntasUsuario.size() <= cantidad) {
            return preguntasUsuario != null ? preguntasUsuario : new ArrayList<>();
        }
        Collections.shuffle(preguntasUsuario);
        return preguntasUsuario.subList(0, cantidad);
    }
}

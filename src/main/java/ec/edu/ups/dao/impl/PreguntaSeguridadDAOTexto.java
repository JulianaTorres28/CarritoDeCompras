package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaSeguridadDAO;
import ec.edu.ups.modelo.PreguntaSeguridad;

import java.io.*;
import java.util.*;

/**
 * Implementación de la interfaz PreguntaSeguridadDAO que utiliza archivos de texto plano
 * para almacenar preguntas base y preguntas de seguridad asociadas a usuarios.
 */
public class PreguntaSeguridadDAOTexto implements PreguntaSeguridadDAO {

    /** Ruta del archivo donde se almacenan las preguntas de seguridad por usuario. */
    private final String archivoPreguntasUsuario;

    /** Lista de preguntas base predeterminadas, sin respuestas. */
    private final List<PreguntaSeguridad> preguntasBase;

    /**
     * Constructor que inicializa el DAO con una ruta de archivo específica
     * y configura la lista de preguntas base.
     *
     * @param archivoPreguntasUsuario Ruta del archivo donde se almacenan preguntas por usuario.
     */
    public PreguntaSeguridadDAOTexto(String archivoPreguntasUsuario) {
        this.archivoPreguntasUsuario = archivoPreguntasUsuario;
        preguntasBase = new ArrayList<>();
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
     * Devuelve la lista de preguntas base sin respuestas.
     *
     * @return Lista de preguntas base.
     */
    @Override
    public List<PreguntaSeguridad> obtenerPreguntasBase() {
        return preguntasBase;
    }

    /**
     * Guarda en el archivo las preguntas de seguridad asociadas a un usuario.
     * Se agregan al archivo, sin sobrescribir el contenido existente.
     *
     * @param username Nombre de usuario.
     * @param preguntas Lista de preguntas con sus respuestas.
     */
    @Override
    public void guardarPreguntasPorUsuario(String username, List<PreguntaSeguridad> preguntas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoPreguntasUsuario, true))) {
            for (PreguntaSeguridad ps : preguntas) {
                writer.write(username + "|" + ps.getPregunta() + "|" + ps.getRespuesta());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica si existen preguntas de seguridad registradas para un usuario.
     *
     * @param username Nombre del usuario.
     * @return true si el usuario tiene preguntas registradas, false en caso contrario.
     */
    @Override
    public boolean usuarioExiste(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoPreguntasUsuario))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith(username + "|")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Devuelve una lista de preguntas aleatorias (y sus respuestas) registradas por el usuario.
     *
     * @param username Nombre del usuario.
     * @param cantidad Cantidad de preguntas a devolver.
     * @return Lista aleatoria de preguntas del usuario (máximo la cantidad indicada).
     */
    @Override
    public List<PreguntaSeguridad> obtenerPreguntasAleatorias(String username, int cantidad) {
        List<PreguntaSeguridad> preguntasUsuario = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoPreguntasUsuario))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 3 && partes[0].equals(username)) {
                    preguntasUsuario.add(new PreguntaSeguridad(partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (preguntasUsuario.size() <= cantidad) {
            return preguntasUsuario;
        }

        Collections.shuffle(preguntasUsuario);
        return preguntasUsuario.subList(0, cantidad);
    }
}

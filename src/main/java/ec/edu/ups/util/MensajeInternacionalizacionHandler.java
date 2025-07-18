package ec.edu.ups.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Clase encargada de gestionar la carga y recuperación de mensajes internacionalizados
 * desde archivos de propiedades (.properties) usando {@link ResourceBundle}.
 * <p>
 * Permite cambiar de idioma y país dinámicamente, así como recuperar mensajes con formato.
 */
public class MensajeInternacionalizacionHandler {

    private ResourceBundle bundle;
    private Locale locale;

    /**
     * Constructor que inicializa el handler con el lenguaje y país especificados.
     *
     * @param lenguaje Código del lenguaje (por ejemplo, "es" para español).
     * @param pais     Código del país (por ejemplo, "EC" para Ecuador).
     */
    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        setLenguaje(lenguaje, pais);
    }

    /**
     * Recupera el mensaje asociado a la clave proporcionada desde el archivo de recursos.
     *
     * @param key Clave del mensaje.
     * @return El mensaje correspondiente, o "!key!" si no se encuentra.
     */
    public String get(String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return "!" + key + "!";
        }
    }

    /**
     * Recupera el mensaje con formato, reemplazando los marcadores con los argumentos proporcionados.
     *
     * @param key  Clave del mensaje.
     * @param args Argumentos para insertar en el mensaje.
     * @return El mensaje formateado.
     */
    public String getFormato(String key, Object... args) {
        String pattern = get(key);
        return MessageFormat.format(pattern, args);
    }

    /**
     * Establece el lenguaje y país deseados para cargar el archivo de recursos correspondiente.
     * Si no se encuentra, por defecto usará español de Ecuador (es_EC).
     *
     * @param lenguaje Código del lenguaje (por ejemplo, "en").
     * @param pais     Código del país (por ejemplo, "US").
     */
    public void setLenguaje(String lenguaje, String pais) {
        try {
            this.locale = new Locale(lenguaje, pais);
            this.bundle = ResourceBundle.getBundle("mensajes", locale);
        } catch (MissingResourceException e) {
            System.err.println("Error: No se pudo encontrar el archivo de recursos para el idioma: " + lenguaje + "_" + pais);
            this.locale = new Locale("es", "EC");
            this.bundle = ResourceBundle.getBundle("mensajes", locale);
        }
    }

    /**
     * Devuelve el {@link Locale} actualmente utilizado por el handler.
     *
     * @return El objeto {@link Locale} actual.
     */
    public Locale getLocale() {
        return locale;
    }
}

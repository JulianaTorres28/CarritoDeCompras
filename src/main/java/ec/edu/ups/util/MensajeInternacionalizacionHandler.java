package ec.edu.ups.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MensajeInternacionalizacionHandler {
    private ResourceBundle bundle;
    private Locale locale;

    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        setLenguaje(lenguaje, pais);
    }

    public String get(String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return "!" + key + "!";
        }
    }

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

    public Locale getLocale() {
        return locale;
    }
}
package ec.edu.ups.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase utilitaria que proporciona métodos para formatear montos monetarios y fechas
 * según la configuración regional (locale) especificada.
 */
public class FormateadorUtils {

    /**
     * Formatea una cantidad numérica como moneda, utilizando el formato del {@link Locale} proporcionado.
     *
     * @param cantidad La cantidad a formatear.
     * @param locale   El locale que determina el formato monetario (por ejemplo, Locale.US, Locale.FRANCE).
     * @return Una cadena con el valor formateado como moneda.
     */
    public static String formatearMoneda(double cantidad, Locale locale) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoneda.format(cantidad);
    }

    /**
     * Formatea una fecha según el estilo medio (MEDIUM) del {@link Locale} especificado.
     *
     * @param fecha  La fecha a formatear.
     * @param locale El locale que determina el formato de la fecha (por ejemplo, Locale.US, Locale.FRANCE).
     * @return Una cadena con la fecha formateada.
     */
    public static String formatearFecha(Date fecha, Locale locale) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return formato.format(fecha);
    }

}

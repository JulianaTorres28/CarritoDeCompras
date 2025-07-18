package ec.edu.ups.util;

/**
 * Clase utilitaria para validar números de cédula ecuatoriana.
 */
public class CedulaValidator {

    /**
     * Valida si una cédula ecuatoriana es correcta según el algoritmo oficial.
     *
     * @param cedula La cédula a validar. Debe contener exactamente 10 dígitos numéricos.
     * @return true si la cédula es válida; false en caso contrario.
     */
    public static boolean validar(String cedula) {
        if (cedula == null || cedula.length() != 10) return false;

        try {
            int provincia = Integer.parseInt(cedula.substring(0, 2));
            int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
            if (provincia < 1 || provincia > 24 || tercerDigito >= 6) return false;

            int[] coef = {2, 1, 2, 1, 2, 1, 2, 1, 2};
            int suma = 0;
            for (int i = 0; i < 9; i++) {
                int valor = Integer.parseInt(cedula.charAt(i) + "") * coef[i];
                suma += (valor > 9) ? valor - 9 : valor;
            }

            int digitoVerificador = Integer.parseInt(cedula.charAt(9) + "");
            int resultado = (suma % 10 == 0) ? 0 : 10 - (suma % 10);
            return digitoVerificador == resultado;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

/**
 * Vista principal de inicio de sesión (Login) del sistema.
 *
 * Esta interfaz gráfica permite a los usuarios:
 * <ul>
 *   <li>Ingresar sus credenciales (nombre de usuario y contraseña)</li>
 *   <li>Registrarse en el sistema</li>
 *   <li>Recuperar su cuenta en caso de olvido de contraseña</li>
 *   <li>Seleccionar el idioma de la interfaz (Español, Inglés, Francés)</li>
 * </ul>
 *
 * Integra soporte para internacionalización (i18n) y cambia los textos dinámicamente
 * según el idioma seleccionado.
 *
 * Componentes destacados:
 * - `txtUsername`: campo para el nombre de usuario
 * - `txtContrasenia`: campo para la contraseña
 * - `btnIniciarSesion`, `btnRegistrarse`, `btnRecuperar`: botones de acción
 * - `cbxIdioma`: selector de idioma
 *
 * Esta clase extiende `JFrame` y es la entrada principal al sistema gráfico.
 *
 * @author Juliana Torres
 * @version 1.0
 */

package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.util.Locale;

public class LoginView extends JFrame {
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    private JPanel panelPrincipal;
    private JPanel panelSecundario;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JComboBox cbxIdioma;
    private JLabel lblRecuperar;
    private JButton btnRecuperar;

    public LoginView(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
        initComponents();
        actualizarTextos();
    }
    private void initComponents() {
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);

        btnIniciarSesion.setIcon(new ImageIcon(getClass().getResource("/iconos/login.png")));
        btnRegistrarse.setIcon(new ImageIcon(getClass().getResource("/iconos/register.png")));
        btnRecuperar.setIcon(new ImageIcon(getClass().getResource("/iconos/forgot_password.png")));

        cbxIdioma.addItem("Español");
        cbxIdioma.addItem("English");
        cbxIdioma.addItem("Français");


        Locale localeActual = mensajeInternacionalizacionHandler.getLocale();
        switch(localeActual.getLanguage()) {
            case "es":
                cbxIdioma.setSelectedItem("Español");
                break;
            case "en":
                cbxIdioma.setSelectedItem("English");
                break;
            case "fr":
                cbxIdioma.setSelectedItem("Français");
                break;
        }

        cbxIdioma.addActionListener(e -> {
            String seleccion = (String) cbxIdioma.getSelectedItem();
            if (seleccion != null) {
                switch(seleccion) {
                    case "Español":
                        mensajeInternacionalizacionHandler.setLenguaje("es", "EC");
                        break;
                    case "English":
                        mensajeInternacionalizacionHandler.setLenguaje("en", "US");
                        break;
                    case "Français":
                        mensajeInternacionalizacionHandler.setLenguaje("fr", "FR");
                        break;
                }
                actualizarTextos();
            }
        });
    }


        private void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("login.titulo"));
        lblUsername.setText(mensajeInternacionalizacionHandler.get("login.username"));
        lblPassword.setText(mensajeInternacionalizacionHandler.get("login.password"));
        btnIniciarSesion.setText(mensajeInternacionalizacionHandler.get("login.ingresar"));
        btnRegistrarse.setText(mensajeInternacionalizacionHandler.get("login.registrarse"));
        lblRecuperar.setText(mensajeInternacionalizacionHandler.get("login.recuperar"));
        btnRecuperar.setText(mensajeInternacionalizacionHandler.get("login.recuperar.btn"));
    }


    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JPanel getPanelSecundario() {
        return panelSecundario;
    }

    public void setPanelSecundario(JPanel panelSecundario) {
        this.panelSecundario = panelSecundario;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public void setTxtContrasenia(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public void setBtnRegistrarse(JButton btnRegistrarse) {
        this.btnRegistrarse = btnRegistrarse;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mensajeInternacionalizacionHandler;
    }

    public void setMensajeInternacionalizacionHandler(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
    }

    public JLabel getLblUsername() {
        return lblUsername;
    }

    public void setLblUsername(JLabel lblUsername) {
        this.lblUsername = lblUsername;
    }

    public JLabel getLblPassword() {
        return lblPassword;
    }

    public void setLblPassword(JLabel lblPassword) {
        this.lblPassword = lblPassword;
    }

    public JComboBox getCbxIdioma() {
        return cbxIdioma;
    }

    public void setCbxIdioma(JComboBox cbxIdioma) {
        this.cbxIdioma = cbxIdioma;
    }

    public JLabel getLblRecuperar() {
        return lblRecuperar;
    }

    public void setLblRecuperar(JLabel lblRecuperar) {
        this.lblRecuperar = lblRecuperar;
    }

    public JButton getBtnRecuperar() {
        return btnRecuperar;
    }

    public void setBtnRecuperar(JButton btnRecuperar) {
        this.btnRecuperar = btnRecuperar;
    }

    public void mostrarMensaje(String clave) {
        JOptionPane.showMessageDialog(this, mensajeInternacionalizacionHandler.get(clave));
    }
    public void limpiarCampos() {
        txtUsername.setText("");
        txtContrasenia.setText("");
    }
}
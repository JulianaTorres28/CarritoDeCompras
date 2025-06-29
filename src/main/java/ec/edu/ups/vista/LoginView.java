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

    public LoginView(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
        initComponents();
        actualizarTextos();
    }
    private void initComponents() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        
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

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void limpiarCampos() {
        txtUsername.setText("");
        txtContrasenia.setText("");
    }
}
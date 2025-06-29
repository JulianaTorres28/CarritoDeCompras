package ec.edu.ups.vista;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class UsuarioRegistroView extends JFrame {

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    private JPanel panelPrincipal;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JComboBox<Rol> cbxRol;
    private JButton btnGuardar;
    private JLabel lblUsername;
    private JLabel lblContrasenia;
    private JLabel lblRol;

    public UsuarioRegistroView(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
        initComponents();
        actualizarTextos();
    }
    private void initComponents() {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panelPrincipal);

        cbxRol.removeAllItems();
        for (Rol rol : Rol.values()) {
            cbxRol.addItem(rol);
        }
    }

    private void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("usuario.registro.titulo"));
        lblUsername.setText(mensajeInternacionalizacionHandler.get("usuario.registro.username"));
        lblContrasenia.setText(mensajeInternacionalizacionHandler.get("usuario.registro.contrasenia"));
        lblRol.setText(mensajeInternacionalizacionHandler.get("usuario.registro.rol"));
        btnGuardar.setText(mensajeInternacionalizacionHandler.get("usuario.registro.guardar"));
    }

    public void cambiarIdioma() {
        actualizarTextos();
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JComboBox<Rol> getCbxRol() {
        return cbxRol;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void mostrarMensaje(String clave) {
        JOptionPane.showMessageDialog(this, mensajeInternacionalizacionHandler.get(clave));
    }
}

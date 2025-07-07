package ec.edu.ups.vista;

import ec.edu.ups.modelo.PreguntaSeguridad;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.util.List;

public class UsuarioRegistroView extends JFrame {

    private JPanel panelPrincipal;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JComboBox<Rol> cbxRol;
    private JButton btnGuardar;
    private JLabel lblUsername;
    private JLabel lblContrasenia;
    private JLabel lblRol;
    private JLabel lblPreguntas;
    private JComboBox cbxPregunta1;
    private JTextField txtRespuesta1;
    private JComboBox cbxPregunta2;
    private JTextField txtRespuesta2;
    private JComboBox cbxPregunta3;
    private JTextField txtRespuesta3;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacion;

    public void setMensajeInternacionalizacion(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
        actualizarTextos();
    }


    public UsuarioRegistroView(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;

        setContentPane(panelPrincipal);
        setTitle("Registrar Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(true);

        cbxRol.removeAllItems();
        for (Rol rol : Rol.values()) {
            cbxRol.addItem(rol);
        }

        actualizarTextos();
    }


    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacion.get("usuario.registro.titulo"));
        lblUsername.setText(mensajeInternacionalizacion.get("usuario.registro.username"));
        lblContrasenia.setText(mensajeInternacionalizacion.get("usuario.registro.contrasenia"));
        lblRol.setText(mensajeInternacionalizacion.get("usuario.registro.rol"));
        lblPreguntas.setText(mensajeInternacionalizacion.get("usuario.registro.preguntas"));
        btnGuardar.setText(mensajeInternacionalizacion.get("usuario.registro.guardar"));
    }


    public void cambiarIdioma() {
        actualizarTextos();
    }

    public void setPreguntasBase(List<PreguntaSeguridad> preguntas) {
        cbxPregunta1.removeAllItems();
        cbxPregunta2.removeAllItems();
        cbxPregunta3.removeAllItems();

        for (PreguntaSeguridad p : preguntas) {
            String traduccion = mensajeInternacionalizacion.get(p.getPregunta());
            cbxPregunta1.addItem(traduccion);
            cbxPregunta2.addItem(traduccion);
            cbxPregunta3.addItem(traduccion);
        }
    }




    public void mostrarMensaje(String clave) {
        JOptionPane.showMessageDialog(this, mensajeInternacionalizacion.get(clave));
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
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

    public JLabel getLblUsername() {
        return lblUsername;
    }

    public JLabel getLblContrasenia() {
        return lblContrasenia;
    }

    public JLabel getLblRol() {
        return lblRol;
    }

    public JLabel getLblPreguntas() {
        return lblPreguntas;
    }

    public JComboBox getCbxPregunta1() {
        return cbxPregunta1;
    }

    public JTextField getTxtRespuesta1() {
        return txtRespuesta1;
    }

    public JComboBox getCbxPregunta2() {
        return cbxPregunta2;
    }

    public JTextField getTxtRespuesta2() {
        return txtRespuesta2;
    }

    public JComboBox getCbxPregunta3() {
        return cbxPregunta3;
    }

    public JTextField getTxtRespuesta3() {
        return txtRespuesta3;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() {
        return mensajeInternacionalizacion;
    }
}

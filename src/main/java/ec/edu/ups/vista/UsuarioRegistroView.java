/**
 * Ventana de registro de usuarios en el sistema.
 *
 * Esta clase permite a los nuevos usuarios registrarse proporcionando los siguientes datos:
 * <ul>
 *   <li>Nombre de usuario</li>
 *   <li>Cédula</li>
 *   <li>Contraseña</li>
 *   <li>Rol (ADMIN, CLIENTE, etc.)</li>
 *   <li>Preguntas de seguridad con sus respectivas respuestas</li>
 * </ul>
 *
 * La clase utiliza un sistema de internacionalización para traducir los textos y etiquetas
 * mediante la clase {@link MensajeInternacionalizacionHandler}. También incluye íconos gráficos
 * para una mejor interfaz visual.
 *
 * Componentes principales:
 * <ul>
 *   <li>`txtUsername`, `txtContrasenia`, `txtCedula`: campos de entrada de datos</li>
 *   <li>`cbxRol`: lista desplegable para seleccionar el rol</li>
 *   <li>`cbxPregunta1`, `cbxPregunta2`, `cbxPregunta3`: listas desplegables de preguntas de seguridad</li>
 *   <li>`txtRespuesta1`, `txtRespuesta2`, `txtRespuesta3`: campos para respuestas de seguridad</li>
 *   <li>`btnGuardar`: botón para guardar el usuario</li>
 * </ul>
 *
 * También proporciona métodos para limpiar campos, mostrar mensajes, cambiar idioma y
 * establecer las preguntas base en los `ComboBox`.
 *
 * @author Juliana Torres
 * @version 1.0
 */

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
    private JLabel lblCedula;
    private JTextField txtCedula;

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

        btnGuardar.setIcon(new ImageIcon(getClass().getResource("/iconos/save.png")));
        lblUsername.setIcon(new ImageIcon(getClass().getResource("/iconos/username.png")));
        lblCedula.setIcon(new ImageIcon(getClass().getResource("/iconos/id.png")));
        lblContrasenia.setIcon(new ImageIcon(getClass().getResource("/iconos/password.png")));
        lblPreguntas.setIcon(new ImageIcon(getClass().getResource("/iconos/question.png")));
    }


    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacion.get("usuario.registro.titulo"));
        lblUsername.setText(mensajeInternacionalizacion.get("usuario.registro.username"));
        lblCedula.setText(mensajeInternacionalizacion.get("usuario.registro.cedula"));
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

    public JLabel getLblCedula() {
        return lblCedula;
    }
    public JTextField getTxtCedula() {
        return txtCedula;
    }
    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
    }
    public void setLblCedula(JLabel lblCedula) {
        this.lblCedula = lblCedula;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() {
        return mensajeInternacionalizacion;
    }

    public void limpiarCampos() {
        txtUsername.setText("");
        txtCedula.setText("");
        txtContrasenia.setText("");
        cbxRol.setSelectedIndex(0);
        cbxPregunta1.setSelectedIndex(0);
        cbxPregunta2.setSelectedIndex(0);
        cbxPregunta3.setSelectedIndex(0);
        txtRespuesta1.setText("");
        txtRespuesta2.setText("");
        txtRespuesta3.setText("");
    }

}

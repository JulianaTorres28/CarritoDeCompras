/**
 * Vista gráfica para recuperar la cuenta de usuario mediante preguntas de seguridad.
 *
 * Esta clase permite:
 * <ul>
 *   <li>Verificar si el nombre de usuario ingresado existe en el sistema.</li>
 *   <li>Mostrar preguntas de seguridad asociadas al usuario.</li>
 *   <li>Verificar respuestas y permitir establecer una nueva contraseña.</li>
 *   <li>Internacionalizar todos los textos visibles mediante {@link MensajeInternacionalizacionHandler}.</li>
 * </ul>
 *
 * Componentes gráficos:
 * <ul>
 *   <li>`txtUsuario`: Campo para ingresar el nombre de usuario.</li>
 *   <li>`btnVerificarUsuario`: Botón para comprobar si el usuario existe y mostrar preguntas.</li>
 *   <li>`lblPregunta1`, `lblPregunta2`, `lblPregunta3`: Etiquetas con las preguntas de seguridad.</li>
 *   <li>`txtRespuesta1`, `txtRespuesta2`, `txtRespuesta3`: Campos para ingresar las respuestas.</li>
 *   <li>`btnVerificarRespuestas`: Botón para validar las respuestas ingresadas.</li>
 *   <li>`lblNuevaContrasenia`, `txtNuevaContrasenia`, `btnGuardarNuevaContrasenia`: Campos para establecer una nueva contraseña.</li>
 *   <li>`lblMensaje`: Etiqueta para mostrar mensajes informativos al usuario.</li>
 * </ul>
 *
 * Esta ventana extiende de `JFrame` y se muestra de forma independiente del resto del sistema.
 *
 * @author Juliana Torres
 * @version 1.0
 */

package ec.edu.ups.vista;

import ec.edu.ups.modelo.PreguntaSeguridad;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.util.List;

public class RecuperarCuentaView extends JFrame {

    private JPanel panelPrincipal;

    private JLabel lblUsuario;
    private JTextField txtUsuario;
    private JButton btnVerificarUsuario;

    private JLabel lblPregunta1;
    private JTextField txtRespuesta1;

    private JLabel lblPregunta2;
    private JTextField txtRespuesta2;

    private JLabel lblPregunta3;
    private JTextField txtRespuesta3;

    private JButton btnVerificarRespuestas;
    private JLabel lblMensaje;
    private JLabel lblNuevaContrasenia;
    private JTextField txtNuevaContrasenia;
    private JButton btnGuardarNuevaContrasenia;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacion;

    public void setMensajeInternacionalizacion(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
    }

    public RecuperarCuentaView(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;

        setContentPane(panelPrincipal);
        setTitle(mensajeInternacionalizacion.get("recuperar.titulo"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 500);

        actualizarTextos();
        setPreguntasVisibles(false);
    }

    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacion.get("recuperar.titulo"));
        lblUsuario.setText(mensajeInternacionalizacion.get("recuperar.lbl.usuario"));
        btnVerificarUsuario.setText(mensajeInternacionalizacion.get("recuperar.btn.verificar.usuario"));

        if (lblPregunta1.isVisible() && lblPregunta1.getName() != null) {
            lblPregunta1.setText(mensajeInternacionalizacion.get(lblPregunta1.getName()));
        }
        if (lblPregunta2.isVisible() && lblPregunta2.getName() != null) {
            lblPregunta2.setText(mensajeInternacionalizacion.get(lblPregunta2.getName()));
        }
        if (lblPregunta3.isVisible() && lblPregunta3.getName() != null) {
            lblPregunta3.setText(mensajeInternacionalizacion.get(lblPregunta3.getName()));
        }
    }



    public void setPreguntas(List<PreguntaSeguridad> preguntas) {
        if (preguntas.size() >= 3) {
            lblPregunta1.setName(preguntas.get(0).getPregunta());
            lblPregunta2.setName(preguntas.get(1).getPregunta());
            lblPregunta3.setName(preguntas.get(2).getPregunta());

            lblPregunta1.setText(mensajeInternacionalizacion.get(lblPregunta1.getName()));
            lblPregunta2.setText(mensajeInternacionalizacion.get(lblPregunta2.getName()));
            lblPregunta3.setText(mensajeInternacionalizacion.get(lblPregunta3.getName()));

            setPreguntasVisibles(true);
        }
    }



    public void setPreguntasVisibles(boolean visible) {
        lblPregunta1.setVisible(visible);
        txtRespuesta1.setVisible(visible);
        lblPregunta2.setVisible(visible);
        txtRespuesta2.setVisible(visible);
        lblPregunta3.setVisible(visible);
        txtRespuesta3.setVisible(visible);
        btnVerificarRespuestas.setVisible(visible);

        lblNuevaContrasenia.setVisible(false);
        txtNuevaContrasenia.setVisible(false);
        btnGuardarNuevaContrasenia.setVisible(false);
    }

    public void mostrarCamposNuevaContrasenia() {
        lblNuevaContrasenia.setVisible(true);
        txtNuevaContrasenia.setVisible(true);
        btnGuardarNuevaContrasenia.setVisible(true);
    }

    public void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensajeInternacionalizacion.get(mensaje));
    }


    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JButton getBtnVerificarUsuario() {
        return btnVerificarUsuario;
    }

    public JLabel getLblPregunta1() {
        return lblPregunta1;
    }

    public JTextField getTxtRespuesta1() {
        return txtRespuesta1;
    }

    public JLabel getLblPregunta2() {
        return lblPregunta2;
    }

    public JTextField getTxtRespuesta2() {
        return txtRespuesta2;
    }

    public JLabel getLblPregunta3() {
        return lblPregunta3;
    }

    public JTextField getTxtRespuesta3() {
        return txtRespuesta3;
    }

    public JButton getBtnVerificarRespuestas() {
        return btnVerificarRespuestas;
    }

    public JLabel getLblMensaje() {
        return lblMensaje;
    }

    public JTextField getTxtNuevaContrasenia() {
        return txtNuevaContrasenia;
    }

    public JButton getBtnGuardarNuevaContrasenia() {
        return btnGuardarNuevaContrasenia;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() {
        return mensajeInternacionalizacion;
    }
}

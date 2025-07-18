/**
 * Vista gráfica administrativa para gestionar usuarios dentro del sistema.
 *
 * Esta clase permite al administrador:
 * <ul>
 *   <li>Buscar usuarios por nombre de usuario.</li>
 *   <li>Editar roles de usuarios existentes.</li>
 *   <li>Actualizar la tabla de usuarios.</li>
 *   <li>Eliminar usuarios seleccionados.</li>
 *   <li>Internacionalizar la interfaz con {@link MensajeInternacionalizacionHandler}.</li>
 * </ul>
 *
 * Componentes clave:
 * <ul>
 *   <li>`tblUsuarios`: Tabla que muestra el nombre de usuario y su rol.</li>
 *   <li>`txtusername`: Campo para editar o mostrar el nombre de usuario.</li>
 *   <li>`cbxRol`: ComboBox para seleccionar y cambiar el rol de un usuario.</li>
 *   <li>`btnBuscar`, `btnEditar`, `btnActualizar`, `btnEliminar`: Botones para operaciones CRUD.</li>
 * </ul>
 *
 * Al seleccionar una fila de la tabla, se cargan los datos del usuario en los campos correspondientes.
 * Esta clase extiende `JInternalFrame`, por lo que puede ser utilizada dentro de un contenedor MDI.
 *
 * @author Juliana Torres
 * @version 1.0
 */

package ec.edu.ups.vista;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuarioAdminView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTable tblUsuarios;
    private JComboBox<Rol> cbxRol;
    private JTextField txtcontrasenia;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnEditar;
    private JTextField txtusername;
    private JButton btnEliminar;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacion;


    public void setMensajeInternacionalizacion(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
    }

    public UsuarioAdminView(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
        setContentPane(panelPrincipal);
        setTitle("Eliminar Productos");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {
                mensajeInternacionalizacion.get("usuario.admin.tabla.username"),
                mensajeInternacionalizacion.get("usuario.admin.tabla.rol")
        };
        modelo.setColumnIdentifiers(columnas);
        tblUsuarios.setModel(modelo);

        cbxRol.setModel(new DefaultComboBoxModel<>(Rol.values()));

        tblUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = tblUsuarios.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    String username = tblUsuarios.getValueAt(filaSeleccionada, 0).toString();
                    String rol = tblUsuarios.getValueAt(filaSeleccionada, 1).toString();

                    txtusername.setText(username);
                    cbxRol.setSelectedItem(Rol.valueOf(rol));
                }
            }
        });

        actualizarTextos();
    }


    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacion.get("usuario.admin.titulo"));
        btnBuscar.setText(mensajeInternacionalizacion.get("usuario.admin.boton.buscar"));
        btnActualizar.setText(mensajeInternacionalizacion.get("usuario.admin.boton.actualizar"));
        btnEditar.setText(mensajeInternacionalizacion.get("usuario.admin.boton.editar"));
        btnEliminar.setText(mensajeInternacionalizacion.get("usuario.admin.boton.eliminar"));

        modelo.setColumnIdentifiers(new Object[]{
                mensajeInternacionalizacion.get("usuario.admin.tabla.username"),
                mensajeInternacionalizacion.get("usuario.admin.tabla.rol")
        });
        modelo.fireTableStructureChanged();
    }

    public void cambiarIdioma() {
        actualizarTextos();
    }


    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTable getTblUsuarios() {
        return tblUsuarios;
    }

    public void setTblUsuarios(JTable tblUsuarios) {
        this.tblUsuarios = tblUsuarios;
    }

    public JComboBox<Rol> getCbxRol() {
        return cbxRol;
    }

    public void setCbxRol(JComboBox<Rol> cbxRol) {
        this.cbxRol = cbxRol;
    }

    public JTextField getTxtcontrasenia() {
        return txtcontrasenia;
    }

    public void setTxtcontrasenia(JTextField txtcontrasenia) {
        this.txtcontrasenia = txtcontrasenia;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public void setBtnEditar(JButton btnEditar) {
        this.btnEditar = btnEditar;
    }

    public JTextField getTxtusername() {
        return txtusername;
    }

    public void setTxtusername(JTextField txtusername) {
        this.txtusername = txtusername;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() {
        return mensajeInternacionalizacion;
    }

    public void cargarDatos(List<Usuario> usuarios) {
        modelo.setRowCount(0);
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{u.getUsername(), u.getRol()});
        }
    }

    public void mostrarMensaje(String clave) {
        JOptionPane.showMessageDialog(this, mensajeInternacionalizacion.get(clave));
    }
}

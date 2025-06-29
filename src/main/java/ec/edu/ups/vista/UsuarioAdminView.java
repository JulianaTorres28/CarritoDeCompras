package ec.edu.ups.vista;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuarioAdminView extends JInternalFrame {
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    private JPanel panelPrincipal;
    private JTable tblUsuarios;
    private JComboBox cbxRol;
    private JTextField txtcontrasenia;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JTextField txtusername;
    private DefaultTableModel modelo;

    public UsuarioAdminView(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler){
        super("", true, true, true, true);
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;

        btnEditar = new JButton();
        btnBuscar = new JButton();
        btnActualizar = new JButton();
        btnEliminar = new JButton();

        initComponents();
        actualizarTextos();
    }

    private void initComponents() {
        setSize(600, 400);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel();
        Object[] columnas = {
                mensajeInternacionalizacionHandler.get("usuario.admin.tabla.username"),
                mensajeInternacionalizacionHandler.get("usuario.admin.tabla.rol")
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

        setContentPane(panelPrincipal);
    }

    private void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("usuario.admin.titulo"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("usuario.admin.boton.buscar"));
        btnActualizar.setText(mensajeInternacionalizacionHandler.get("usuario.admin.boton.actualizar"));
        btnEditar.setText(mensajeInternacionalizacionHandler.get("usuario.admin.boton.editar"));
        btnEliminar.setText(mensajeInternacionalizacionHandler.get("usuario.admin.boton.eliminar"));

        // Actualizar encabezados de la tabla
        modelo.setColumnIdentifiers(new Object[]{
                mensajeInternacionalizacionHandler.get("usuario.admin.tabla.username"),
                mensajeInternacionalizacionHandler.get("usuario.admin.tabla.rol")
        });
    }

    public void cambiarIdioma() {
        actualizarTextos();
    }


    public JTable getTblUsuarios() {
        return tblUsuarios;
    }

    public void setTblUsuarios(JTable tblUsuarios) {
        this.tblUsuarios = tblUsuarios;
    }

    public JComboBox getCbxRol() {
        return cbxRol;
    }

    public void setCbxRol(JComboBox cbxRol) {
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

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JTextField getTxtusername() {
        return txtusername;
    }

    public void setTxtusername(JTextField txtusername) {
        this.txtusername = txtusername;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void cargarDatos(List<Usuario> usuarios) {
        modelo.setRowCount(0);
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{u.getUsername(), u.getRol()});
        }
    }

    public void mostrarMensaje(String clave) {
        JOptionPane.showMessageDialog(this, mensajeInternacionalizacionHandler.get(clave));
    }

}

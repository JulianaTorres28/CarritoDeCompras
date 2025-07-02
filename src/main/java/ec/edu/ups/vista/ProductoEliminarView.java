package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductoEliminarView extends JInternalFrame{
    private JTextField txtNombre;
    private JLabel lblNombre;
    private JTable tblProductos;
    private JPanel panelPrincipal;
    private JButton btnBuscar;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton btnEliminar;
    private JLabel lblCodigo;
    private JLabel lbNombre;
    private JLabel lblPrecio;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacion;

    public void setMensajeInternacionalizacion(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
    }

    public ProductoEliminarView(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
        setContentPane(panelPrincipal);
        setTitle("Eliminar Productos");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        actualizarTextos();

    }

    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacion.get("producto.eliminar.titulo"));
        btnEliminar.setText(mensajeInternacionalizacion.get("producto.btn.eliminar"));
        btnBuscar.setText(mensajeInternacionalizacion.get("producto.btn.buscar"));
        lblNombre.setText(mensajeInternacionalizacion.get("producto.lbl.nombre"));
        lblCodigo.setText(mensajeInternacionalizacion.get("producto.lbl.codigo"));
        lbNombre.setText(mensajeInternacionalizacion.get("producto.lbl.nombre"));
        lblPrecio.setText(mensajeInternacionalizacion.get("producto.lbl.precio"));

        modelo.setColumnIdentifiers(new String[] {
                mensajeInternacionalizacion.get("producto.columna.codigo"),
                mensajeInternacionalizacion.get("producto.columna.nombre"),
                mensajeInternacionalizacion.get("producto.columna.precio")
        });
        modelo.fireTableStructureChanged();
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JLabel getLbNombre() {
        return lbNombre;
    }

    public void setLbNombre(JLabel lbNombre) {
        this.lbNombre = lbNombre;
    }

    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
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

    public void cargarDatos(String codigo, String nombre, String precio) {
        Object[] fila = {
                codigo,
                nombre,
                precio
        };
        modelo.addRow(fila);
    }
}

package ec.edu.ups.vista;

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
    private JLabel btnCodigo;
    private JLabel btnNombre;
    private JLabel btnPrecio;
    private DefaultTableModel modelo;

    public ProductoEliminarView() {
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

    public JLabel getBtnCodigo() {
        return btnCodigo;
    }

    public void setBtnCodigo(JLabel btnCodigo) {
        this.btnCodigo = btnCodigo;
    }

    public JLabel getBtnNombre1() {
        return btnNombre;
    }

    public void setBtnNombre1(JLabel btnNombre1) {
        this.btnNombre = btnNombre1;
    }

    public JLabel getBtnPrecio() {
        return btnPrecio;
    }

    public void setBtnPrecio(JLabel btnPrecio) {
        this.btnPrecio = btnPrecio;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
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

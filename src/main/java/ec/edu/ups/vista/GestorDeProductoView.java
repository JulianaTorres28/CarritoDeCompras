package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GestorDeProductoView extends JFrame{
    private JLabel labelCodigo;
    private JTable tblProductos;
    private JTextField txtBuscar;
    private JButton buscarButton;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JLabel labelPrecio;
    private JLabel labelNombre;
    private JPanel panelGestor;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private DefaultTableModel modeloTabla;


    public GestorDeProductoView() {
        setContentPane(panelGestor);
        setTitle("Gestor de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        modeloTabla = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio"};
        modeloTabla.setColumnIdentifiers(columnas);
        tblProductos.setModel(modeloTabla);

    }

    public JTextField getTxtBuscar() {return txtBuscar;}

    public void setTxtBuscar(JTextField txtBuscar) {this.txtBuscar = txtBuscar;}

    public JButton getBuscarButton() { return buscarButton;}

    public void setBuscarButton(JButton buscarButton) {this.buscarButton = buscarButton;}

    public JTable getTblProductos() {return tblProductos;}

    public void setTblProductos(JTable tblProductos) {this.tblProductos = tblProductos;}

    public DefaultTableModel getModeloTabla() {return modeloTabla;}

    public void setModeloTabla(DefaultTableModel modeloTabla) {this.modeloTabla = modeloTabla;}

    public JLabel getLabelCodigo() {return labelCodigo;}

    public void setLabelCodigo(JLabel labelCodigo) {this.labelCodigo = labelCodigo;}

    public JLabel getLabelNombre() {return labelNombre;}

    public void setLabelNombre(JLabel labelNombre) {this.labelNombre = labelNombre;}

    public JLabel getLabelPrecio() {return labelPrecio;}

    public void setLabelPrecio(JLabel labelPrecio) {this.labelPrecio = labelPrecio;}

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JTextField getTxtNombre() {return txtNombre;}

    public void setTxtNombre(JTextField txtNombre) {this.txtNombre = txtNombre;}

    public JTextField getTxtPrecio() {return txtPrecio;}

    public void setTxtPrecio(JTextField txtPrecio) {this.txtPrecio = txtPrecio;}

    public void cargarDatos(List<Producto> listarProductos) {
        modeloTabla.setNumRows(0);
        for (Producto producto : listarProductos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            };
            modeloTabla.addRow(fila);
        }
    }

    public JButton getActualizarButton() {
        return actualizarButton;
    }

    public void setActualizarButton(JButton actualizarButton) {
        this.actualizarButton = actualizarButton;
    }

    public JButton getEliminarButton() {
        return eliminarButton;
    }

    public void setEliminarButton(JButton eliminarButton) {
        this.eliminarButton = eliminarButton;
    }
}
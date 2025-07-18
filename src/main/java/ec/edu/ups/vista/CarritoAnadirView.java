/**
 * Ventana interna de la aplicación que permite al usuario añadir productos a un carrito de compras.
 * Presenta una interfaz gráfica con campos de entrada y botones para:
 * <ul>
 *     <li>Buscar productos por código</li>
 *     <li>Añadir productos al carrito con una cantidad seleccionada</li>
 *     <li>Eliminar productos del carrito</li>
 *     <li>Actualizar cantidades</li>
 *     <li>Guardar el carrito</li>
 *     <li>Limpiar todos los campos</li>
 * </ul>
 *
 * Además, muestra el subtotal, IVA y total calculado en tiempo real,
 * y permite la internacionalización de la interfaz gráfica.
 *
 * Esta clase forma parte del módulo de ventas de la aplicación.
 *
 * @author Juliana Torres
 * @version 1.0
 */

package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoAnadirView extends JInternalFrame {
    private JButton btnBuscar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnAnadir;
    private JTable tblProductos;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JComboBox cbxCantidad;
    private JPanel panelPrincipal;
    private JButton btnEliminarItem;
    private JButton btnActualizarCantidad;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JLabel lblSubtotal;
    private JLabel lblIva;
    private JLabel lblTotal;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacion;

    public void setMensajeInternacionalizacion(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
    }

    public CarritoAnadirView(MensajeInternacionalizacionHandler mensajeInternacionalizacion){
        super("Carrito de Compras", true, true, false, true);
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{
                mensajeInternacionalizacion.get("carrito.columna.codigo"),
                mensajeInternacionalizacion.get("carrito.columna.nombre"),
                mensajeInternacionalizacion.get("carrito.columna.precio"),
                mensajeInternacionalizacion.get("carrito.columna.cantidad"),
                mensajeInternacionalizacion.get("carrito.columna.subtotal")
        });
        tblProductos.setModel(modelo);

        cargarDatos();
        actualizarTextos();
    }

    public void actualizarTextos(){
        System.out.println("Actualizando textos del carrito...");

        setTitle(mensajeInternacionalizacion.get("carrito.titulo"));


        btnBuscar.setText(mensajeInternacionalizacion.get("carrito.btn.buscar"));
        btnAnadir.setText(mensajeInternacionalizacion.get("carrito.btn.anadir"));
        btnGuardar.setText(mensajeInternacionalizacion.get("carrito.btn.guardar"));
        btnLimpiar.setText(mensajeInternacionalizacion.get("carrito.btn.limpiar"));
        btnEliminarItem.setText(mensajeInternacionalizacion.get("carrito.btn.eliminar"));
        btnActualizarCantidad.setText(mensajeInternacionalizacion.get("carrito.btn.actualizar"));
        lblCodigo.setText(mensajeInternacionalizacion.get("carrito.lbl.codigo"));
        lblNombre.setText(mensajeInternacionalizacion.get("carrito.lbl.nombre"));
        lblPrecio.setText(mensajeInternacionalizacion.get("carrito.lbl.precio"));
        lblCantidad.setText(mensajeInternacionalizacion.get("carrito.lbl.cantidad"));
        lblSubtotal.setText(mensajeInternacionalizacion.get("carrito.lbl.subtotal"));
        lblIva.setText(mensajeInternacionalizacion.get("carrito.lbl.iva"));
        lblTotal.setText(mensajeInternacionalizacion.get("carrito.lbl.total"));

        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.setColumnIdentifiers(new String[]{
                mensajeInternacionalizacion.get("carrito.columna.codigo"),
                mensajeInternacionalizacion.get("carrito.columna.nombre"),
                mensajeInternacionalizacion.get("carrito.columna.precio"),
                mensajeInternacionalizacion.get("carrito.columna.cantidad"),
                mensajeInternacionalizacion.get("carrito.columna.subtotal")
        });
        modelo.fireTableStructureChanged();
    }

    private void cargarDatos(){
        cbxCantidad.removeAllItems();
        for(int i = 0; i < 20; i++){
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JComboBox getCbxCantidad() {
        return cbxCantidad;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JButton getBtnEliminarItem() {return btnEliminarItem;}

    public void setBtnEliminarItem(JButton btnEliminarItem) {this.btnEliminarItem = btnEliminarItem;}

    public JButton getBtnActualizarCantidad() {return btnActualizarCantidad;}

    public void setBtnActualizarCantidad(JButton btnActualizarCantidad) {this.btnActualizarCantidad = btnActualizarCantidad;}

    public JLabel getLblCodigo() {return lblCodigo;}

    public void setLblCodigo(JLabel lblCodigo) {this.lblCodigo = lblCodigo;}

    public JLabel getLblNombre() {return lblNombre;}

    public void setLblNombre(JLabel lblNombre) {this.lblNombre = lblNombre;}

    public JLabel getLblPrecio() {return lblPrecio;}

    public void setLblPrecio(JLabel lblPrecio) {this.lblPrecio = lblPrecio;}

    public JLabel getLblCantidad() {return lblCantidad;}

    public void setLblCantidad(JLabel lblCantidad) {this.lblCantidad = lblCantidad;}

    public JLabel getLblSubtotal() {return lblSubtotal;}

    public void setLblSubtotal(JLabel lblSubtotal) {this.lblSubtotal = lblSubtotal;}

    public JLabel getLblIva() {return lblIva;}

    public void setLblIva(JLabel lblIva) {this.lblIva = lblIva;}

    public JLabel getLblTotal() {return lblTotal;}

    public void setLblTotal(JLabel lblTotal) {this.lblTotal = lblTotal;}

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() {
        return mensajeInternacionalizacion;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}

/**
 * Vista gráfica para listar y buscar productos registrados en el sistema.
 *
 * Esta clase permite:
 * <ul>
 *   <li>Visualizar una tabla con todos los productos disponibles.</li>
 *   <li>Buscar productos por nombre o criterio ingresado.</li>
 *   <li>Cambiar dinámicamente los textos mediante soporte de internacionalización.</li>
 * </ul>
 *
 * Componentes gráficos principales:
 * - `txtBuscar`: Campo para ingresar el criterio de búsqueda.
 * - `btnBuscar`: Botón para ejecutar la búsqueda.
 * - `btnListar`: Botón para mostrar todos los productos.
 * - `tblProductos`: Tabla donde se muestra el listado de productos.
 * - `lblNombre`: Etiqueta descriptiva del campo de búsqueda.
 *
 * Utiliza `MensajeInternacionalizacionHandler` para adaptar la interfaz gráfica
 * al idioma seleccionado por el usuario.
 *
 * Extiende de `JInternalFrame`, lo cual permite integrar esta vista en un `JDesktopPane`.
 *
 * @author Juliana Torres
 * @version 1.0
 */

package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoListaView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private JLabel lblNombre;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacion;

    public void setMensajeInternacionalizacion(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
    }

    public ProductoListaView(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;

        setContentPane(panelPrincipal);
        setTitle("Listado de Productos");
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
        setTitle(mensajeInternacionalizacion.get("producto.lista.titulo"));
        btnBuscar.setText(mensajeInternacionalizacion.get("producto.btn.buscar"));
        btnListar.setText(mensajeInternacionalizacion.get("producto.btn.listar"));
        lblNombre.setText(mensajeInternacionalizacion.get("producto.lbl.nombre"));

        modelo.setColumnIdentifiers(new String[]{
                mensajeInternacionalizacion.get("producto.columna.codigo"),
                mensajeInternacionalizacion.get("producto.columna.nombre"),
                mensajeInternacionalizacion.get("producto.columna.precio")
        });
        modelo.fireTableStructureChanged();
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
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

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void cargarDatos(List<Producto> listaProductos) {
        modelo.setNumRows(0);

        for (Producto producto : listaProductos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            };
            modelo.addRow(fila);
        }
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() {
        return mensajeInternacionalizacion;
    }
}

/**
 * Ventana interna que muestra la lista de carritos de compras registrados.
 * Permite al usuario listar todos los carritos almacenados y visualizar:
 * <ul>
 *     <li>Código del carrito</li>
 *     <li>Fecha de creación</li>
 *     <li>Subtotal</li>
 *     <li>IVA</li>
 *     <li>Total</li>
 *     <li>Usuario que creó el carrito</li>
 * </ul>
 *
 * Integra soporte de internacionalización e incluye formato de fecha y moneda
 * según la configuración regional establecida en la aplicación.
 *
 * Esta vista es útil para administración y seguimiento de las compras realizadas.
 *
 * @author Juliana Torres
 * @version 1.0
 */

package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;

public class CarritoListaView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTable tblCarritos;
    private JButton btnListar;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacion;

    public void setMensajeInternacionalizacion(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
    }

    public CarritoListaView(MensajeInternacionalizacionHandler mensajeInternacionalizacion) {
        this.mensajeInternacionalizacion = mensajeInternacionalizacion;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        actualizarTextos();
    }

    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacion.get("carritos.lista.titulo"));
        btnListar.setText(mensajeInternacionalizacion.get("carritos.lista.btn.listar"));

        Object[] columnas = {
                mensajeInternacionalizacion.get("carritos.lista.columna.codigo"),
                mensajeInternacionalizacion.get("carritos.lista.columna.fecha"),
                mensajeInternacionalizacion.get("carritos.lista.columna.subtotal"),
                mensajeInternacionalizacion.get("carritos.lista.columna.iva"),
                mensajeInternacionalizacion.get("carritos.lista.columna.total"),
                mensajeInternacionalizacion.get("carritos.lista.columna.usuario")
        };
        modelo.setColumnIdentifiers(columnas);
        tblCarritos.setModel(modelo);
        modelo.fireTableStructureChanged();
    }



    public JButton getBtnListar() { return btnListar; }

    public JTable getTblCarritos() { return tblCarritos; }

    public void cargarDatos(List<Carrito> listaCarritos, Locale locale) {
        modelo.setRowCount(0);

        for (Carrito c : listaCarritos) {
            modelo.addRow(new Object[]{
                    c.getCodigo(),
                    ec.edu.ups.util.FormateadorUtils.formatearFecha(c.getFechaCreacion().getTime(), locale),
                    ec.edu.ups.util.FormateadorUtils.formatearMoneda(c.calcularSubtotal(), locale),
                    ec.edu.ups.util.FormateadorUtils.formatearMoneda(c.calcularIVA(), locale),
                    ec.edu.ups.util.FormateadorUtils.formatearMoneda(c.calcularTotal(), locale),
                    c.getUsuario().getUsername()
            });
        }
    }
}

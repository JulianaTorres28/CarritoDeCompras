package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;

public class CarritoListaView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTable tblCarritos;
    private JButton btnListar;
    private DefaultTableModel modelo;

    public CarritoListaView() {
        setContentPane(panelPrincipal);
        setTitle("Listado de Carritos");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Fecha", "Subtotal", "IVA", "Total", "Usuario"};
        modelo.setColumnIdentifiers(columnas);
        tblCarritos.setModel(modelo);
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

package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.GestorDeProductoView;
import ec.edu.ups.vista.ProductoAnadirView;
import ec.edu.ups.vista.ProductoListaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final GestorDeProductoView gestorDeProductoView;
    private final ProductoDAO productoDAO;


    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              GestorDeProductoView gestorDeProductoView) {
        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.gestorDeProductoView = gestorDeProductoView;
        configurarEventos();
    }

    private void configurarEventos() {
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });

        gestorDeProductoView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoPorCodigo();
            }
        });
        gestorDeProductoView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = gestorDeProductoView.getTblProductos().getSelectedRow();
                if (filaSeleccionada != -1) {
                    int codigo = Integer.parseInt(gestorDeProductoView.getTblProductos().getValueAt(filaSeleccionada, 0).toString());
                    productoDAO.eliminar(codigo);
                    gestorDeProductoView.getModeloTabla().removeRow(filaSeleccionada);
                    JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto para eliminar");
                }
            }
        });

        gestorDeProductoView.getActualizarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = gestorDeProductoView.getTblProductos().getSelectedRow();
                if (filaSeleccionada != -1) {
                    try {
                        int codigo = Integer.parseInt(gestorDeProductoView.getTblProductos().getValueAt(filaSeleccionada, 0).toString());
                        String nombre = gestorDeProductoView.getTxtNombre().getText();
                        double precio = Double.parseDouble(gestorDeProductoView.getTxtPrecio().getText());

                        Producto productoActualizado = new Producto(codigo, nombre, precio);
                        productoDAO.actualizar(productoActualizado);

                        gestorDeProductoView.getModeloTabla().setValueAt(nombre, filaSeleccionada, 1);
                        gestorDeProductoView.getModeloTabla().setValueAt(precio, filaSeleccionada, 2);

                        JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Error en el formato de precio");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto para actualizar");
                }
            }
        });


    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
    private void buscarProductoPorCodigo() {
        int codigo = Integer.parseInt(gestorDeProductoView.getTxtBuscar().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        gestorDeProductoView.getModeloTabla().setRowCount(0);

        if (producto != null) {
            Object[] fila = {producto.getCodigo(), producto.getNombre(), producto.getPrecio()};
            gestorDeProductoView.getModeloTabla().addRow(fila);
        }
    }
}
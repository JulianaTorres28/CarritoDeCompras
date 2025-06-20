package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final CarritoAnadirView carritoAnadirView;
    private final ProductoActualizarView productoActualizarView;
    private final ProductoEliminarView productoEliminarView;

    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              CarritoAnadirView carritoAnadirView,
                              ProductoActualizarView productoActualizarView,
                              ProductoEliminarView productoEliminarView) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.carritoAnadirView = carritoAnadirView;
        this.productoActualizarView = productoActualizarView;
        this.productoEliminarView = productoEliminarView;
        this.configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
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

        carritoAnadirView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoPorCodigo();
            }
        });

        productoActualizarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarYMostrarProductoEnTabla();
            }
        });

        productoActualizarView.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        productoEliminarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoParaEliminar();
            }
        });

        productoEliminarView.getBtnEliminar().addActionListener(e -> eliminarProductoSeleccionado());

        configurarSeleccionTablaEliminar();
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
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            carritoAnadirView.mostrarMensaje("No se encontro el producto");
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
        } else {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }

    }

    private void buscarYMostrarProductoEnTabla() {
        int codigo = Integer.parseInt(productoActualizarView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        productoActualizarView.getModelo().setRowCount(0);

        if (producto != null) {
            Object[] fila = {producto.getCodigo(), producto.getNombre(), producto.getPrecio()};
            productoActualizarView.getModelo().addRow(fila);
            productoActualizarView.getTxtNombre().setText(producto.getNombre());
            productoActualizarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        } else {
            productoActualizarView.getTxtNombre().setText("");
            productoActualizarView.getTxtPrecio().setText("");
            productoActualizarView.getModelo().setRowCount(0);
            productoActualizarView.mostrarMensaje("Producto no encontrado");
        }
    }

    private void actualizarProducto() {
        try {
            int codigo = Integer.parseInt(productoActualizarView.getTxtCodigo().getText());
            String nombre = productoActualizarView.getTxtNombre().getText();
            double precio = Double.parseDouble(productoActualizarView.getTxtPrecio().getText());

            Producto productoActualizado = new Producto(codigo, nombre, precio);
            productoDAO.actualizar(productoActualizado);

            productoActualizarView.mostrarMensaje("Producto actualizado correctamente");

            productoActualizarView.cargarDatos(productoDAO.listarTodos());

            productoListaView.cargarDatos(productoDAO.listarTodos());

        } catch (NumberFormatException e) {
            productoActualizarView.mostrarMensaje("Error: formato numérico inválido");
        } catch (Exception e) {
            productoActualizarView.mostrarMensaje("Error al actualizar el producto");
        }
    }

    private void buscarProductoParaEliminar() {
        String nombre = productoEliminarView.getTxtNombre().getText();
        List<Producto> productos = productoDAO.buscarPorNombre(nombre);

        productoEliminarView.getModelo().setRowCount(0); // Limpia la tabla

        for (Producto producto : productos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            };
            productoEliminarView.getModelo().addRow(fila);
        }

        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(productoEliminarView, "No se encontraron productos con ese nombre.");
        }
    }

    private void configurarSeleccionTablaEliminar() {
        productoEliminarView.getTblProductos().getSelectionModel().addListSelectionListener(e -> {
            int fila = productoEliminarView.getTblProductos().getSelectedRow();
            if (fila != -1) {
                String codigo = productoEliminarView.getModelo().getValueAt(fila, 0).toString();
                String nombre = productoEliminarView.getModelo().getValueAt(fila, 1).toString();
                String precio = productoEliminarView.getModelo().getValueAt(fila, 2).toString();

                productoEliminarView.getTextField1().setText(codigo);
                productoEliminarView.getTextField2().setText(nombre);
                productoEliminarView.getTextField3().setText(precio);
            }
        });
    }

    private void eliminarProductoSeleccionado() {
        try {
            int codigo = Integer.parseInt(productoEliminarView.getTextField1().getText());

            int confirmacion = JOptionPane.showConfirmDialog(
                    productoEliminarView,
                    "¿Está seguro que desea eliminar este producto?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                productoDAO.eliminar(codigo);

                productoEliminarView.getTextField1().setText("");
                productoEliminarView.getTextField2().setText("");
                productoEliminarView.getTextField3().setText("");
                productoEliminarView.getModelo().setRowCount(0);

                productoListaView.cargarDatos(productoDAO.listarTodos());
                productoActualizarView.cargarDatos(productoDAO.listarTodos());

                JOptionPane.showMessageDialog(productoEliminarView, "Producto eliminado correctamente.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(productoEliminarView, "Ingrese un código válido.");
        }
    }



}
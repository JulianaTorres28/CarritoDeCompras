package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.*;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.CarritoListaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;

/**
 * Controlador encargado de manejar la lógica del carrito de compras.
 * Gestiona la interacción entre la vista, el modelo y los DAOs relacionados.
 */
public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private final CarritoListaView carritoListaView;
    private final Usuario usuarioAutenticado;

    private Carrito carrito;

    /**
     * Constructor del controlador de carrito de compras.
     *
     * @param carritoDAO           DAO para la gestión de carritos.
     * @param productoDAO          DAO para la gestión de productos.
     * @param carritoAnadirView    Vista para añadir productos al carrito.
     * @param carritoListaView     Vista para listar carritos.
     * @param usuarioAutenticado   Usuario autenticado en sesión.
     */
    public CarritoController(
            CarritoDAO carritoDAO,
            ProductoDAO productoDAO,
            CarritoAnadirView carritoAnadirView,
            CarritoListaView carritoListaView,
            Usuario usuarioAutenticado) {

        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carritoListaView = carritoListaView;
        this.usuarioAutenticado = usuarioAutenticado;

        this.carrito = new Carrito();
        this.carrito.setUsuario(usuarioAutenticado);

        configurarEventosEnVistas();
    }

    /**
     * Asocia los eventos de los botones de las vistas con las funciones del controlador.
     */
    private void configurarEventosEnVistas() {
        carritoAnadirView.getBtnAnadir().addActionListener(e -> anadirProducto());
        carritoAnadirView.getBtnGuardar().addActionListener(e -> guardarCarrito());
        carritoListaView.getBtnListar().addActionListener(e -> cargarCarritosEnTabla());
        carritoAnadirView.getBtnEliminarItem().addActionListener(e -> eliminarItem());
        carritoAnadirView.getBtnActualizarCantidad().addActionListener(e -> actualizarCantidad());
        carritoAnadirView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoPorCodigo();
            }
        });
    }

    /**
     * Añade un producto al carrito a partir del código ingresado y la cantidad seleccionada.
     */
    private void anadirProducto() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto == null) {
            carritoAnadirView.mostrarMensaje("Producto no encontrado");
            return;
        }

        int cantidad = Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());
        carrito.agregarProducto(producto, cantidad);

        cargarProductos();
        mostrarTotales();
    }

    /**
     * Guarda el carrito actual en la base de datos y reinicia el formulario.
     */
    private void guardarCarrito() {
        carrito.setUsuario(usuarioAutenticado);
        carritoDAO.crear(carrito);
        carritoAnadirView.mostrarMensaje("Carrito guardado correctamente.");
        carrito = new Carrito();
        carrito.setUsuario(usuarioAutenticado);
        limpiarTablaProductos();
        mostrarTotales();
    }

    /**
     * Carga los productos actuales del carrito en la tabla de la vista.
     */
    private void cargarProductos() {
        Locale locale = carritoAnadirView.getMensajeInternacionalizacion().getLocale();
        var modelo = (javax.swing.table.DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0);

        for (ItemCarrito item : carrito.obtenerItems()) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), locale),
                    item.getCantidad(),
                    FormateadorUtils.formatearMoneda(item.getProducto().getPrecio() * item.getCantidad(), locale)
            });
        }
    }

    /**
     * Muestra los valores del subtotal, IVA y total en la vista.
     */
    private void mostrarTotales() {
        Locale locale = carritoAnadirView.getMensajeInternacionalizacion().getLocale();
        carritoAnadirView.getTxtSubtotal().setText(FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), locale));
        carritoAnadirView.getTxtIva().setText(FormateadorUtils.formatearMoneda(carrito.calcularIVA(), locale));
        carritoAnadirView.getTxtTotal().setText(FormateadorUtils.formatearMoneda(carrito.calcularTotal(), locale));
    }

    /**
     * Carga la lista de carritos disponibles en la tabla de la vista.
     * Si el usuario es administrador, muestra todos los carritos.
     * Si no, solo muestra los del usuario autenticado.
     */
    private void cargarCarritosEnTabla() {
        List<Carrito> carritos;

        if (usuarioAutenticado.getRol().equals(Rol.ADMINISTRADOR)) {
            carritos = carritoDAO.listarTodos();
        } else {
            carritos = carritoDAO.listarTodos().stream()
                    .filter(c -> c.getUsuario().getUsername().equals(usuarioAutenticado.getUsername()))
                    .toList();
        }

        Locale locale = Locale.getDefault();
        carritoListaView.cargarDatos(carritos, locale);
    }

    /**
     * Elimina un producto seleccionado del carrito.
     */
    private void eliminarItem() {
        int fila = carritoAnadirView.getTblProductos().getSelectedRow();
        if (fila >= 0) {
            int codigo = Integer.parseInt(carritoAnadirView.getTblProductos().getValueAt(fila, 0).toString());
            carrito.eliminarProducto(codigo);
            cargarProductos();
            mostrarTotales();
            carritoAnadirView.mostrarMensaje("Ítem eliminado correctamente.");
        } else {
            carritoAnadirView.mostrarMensaje("Seleccione un ítem para eliminar.");
        }
    }

    /**
     * Permite modificar la cantidad de un producto ya añadido al carrito.
     */
    private void actualizarCantidad() {
        int fila = carritoAnadirView.getTblProductos().getSelectedRow();
        if (fila >= 0) {
            int codigo = Integer.parseInt(carritoAnadirView.getTblProductos().getValueAt(fila, 0).toString());
            String nuevaCantidadStr = JOptionPane.showInputDialog("Nueva cantidad:");
            if (nuevaCantidadStr != null && !nuevaCantidadStr.isEmpty()) {
                int nuevaCantidad = Integer.parseInt(nuevaCantidadStr);
                carrito.actualizarCantidadProducto(codigo, nuevaCantidad);
                cargarProductos();
                mostrarTotales();
                carritoAnadirView.mostrarMensaje("Cantidad actualizada correctamente.");
            }
        } else {
            carritoAnadirView.mostrarMensaje("Seleccione un ítem para actualizar.");
        }
    }

    /**
     * Limpia la tabla de productos en la vista.
     */
    private void limpiarTablaProductos() {
        var modelo = (javax.swing.table.DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0);
    }

    /**
     * Busca un producto por su código e imprime su información en la vista.
     */
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
}

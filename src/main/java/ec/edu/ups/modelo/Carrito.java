package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa un carrito de compras asociado a un usuario.
 * <p>
 * Contiene una lista de ítems, fecha de creación, y métodos para agregar,
 * eliminar y actualizar productos, así como para calcular el subtotal, IVA y total.
 * </p>
 * <p>
 * Esta clase implementa la interfaz {@link Serializable} para permitir su almacenamiento
 * en archivos binarios.
 * </p>
 *
 */
public class Carrito implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Usuario al que pertenece el carrito. */
    private Usuario usuario;

    /** Porcentaje de IVA aplicado (12%). */
    private final double IVA = 0.12;

    /** Contador estático para generar códigos únicos de carrito. */
    private static int contador = 1;

    /** Código único del carrito. */
    private int codigo;

    /** Fecha de creación del carrito. */
    private GregorianCalendar fechaCreacion;

    /** Lista de ítems (productos y cantidades) en el carrito. */
    private List<ItemCarrito> items;

    /**
     * Constructor por defecto. Inicializa el carrito con un código único,
     * la fecha actual y una lista vacía de ítems.
     */
    public Carrito() {
        codigo = contador++;
        items = new ArrayList<>();
        fechaCreacion = new GregorianCalendar();
    }

    /**
     * Obtiene el usuario asociado al carrito.
     *
     * @return el usuario del carrito.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al carrito.
     *
     * @param usuario el usuario a asignar.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el código único del carrito.
     *
     * @return el código del carrito.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece el código del carrito.
     *
     * @param codigo el nuevo código del carrito.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene la fecha de creación del carrito.
     *
     * @return la fecha de creación.
     */
    public GregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del carrito.
     *
     * @param fechaCreacion nueva fecha de creación.
     */
    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Agrega un producto al carrito con una cantidad específica.
     *
     * @param producto el producto a agregar.
     * @param cantidad la cantidad del producto.
     */
    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    /**
     * Elimina un producto del carrito por su código.
     *
     * @param codigoProducto el código del producto a eliminar.
     */
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    /**
     * Vacía completamente el carrito.
     */
    public void vaciarCarrito() {
        items.clear();
    }

    /**
     * Obtiene la lista de ítems del carrito.
     *
     * @return lista de ítems.
     */
    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    /**
     * Verifica si el carrito está vacío.
     *
     * @return {@code true} si no tiene ítems, {@code false} en caso contrario.
     */
    public boolean estaVacio() {
        return items.isEmpty();
    }

    /**
     * Calcula el subtotal del carrito (sin incluir IVA).
     *
     * @return el subtotal.
     */
    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    /**
     * Calcula el valor del IVA del carrito.
     *
     * @return el monto del IVA.
     */
    public double calcularIVA() {
        return calcularSubtotal() * IVA;
    }

    /**
     * Calcula el total a pagar (subtotal + IVA).
     *
     * @return el total del carrito.
     */
    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    /**
     * Actualiza la cantidad de un producto específico en el carrito.
     *
     * @param codigoProducto código del producto a modificar.
     * @param nuevaCantidad nueva cantidad a asignar.
     */
    public void actualizarCantidadProducto(int codigoProducto, int nuevaCantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getCodigo() == codigoProducto) {
                item.setCantidad(nuevaCantidad);
                break;
            }
        }
    }

    /**
     * Retorna una representación textual del carrito.
     *
     * @return cadena de texto con la información del carrito.
     */
    @Override
    public String toString() {
        return "Carrito{" +
                "IVA=" + IVA +
                ", codigo=" + codigo +
                ", fechaCreacion=" + fechaCreacion +
                ", items=" + items +
                '}';
    }
}

package ec.edu.ups.modelo;

import java.io.Serializable;

/**
 * Clase que representa un ítem dentro de un carrito de compras.
 * <p>
 * Un ítem contiene un {@link Producto} y la cantidad que el usuario desea comprar.
 * Esta clase también permite calcular el subtotal correspondiente al ítem.
 * </p>
 *
 * <p>Implementa {@link Serializable} para permitir almacenamiento en archivos binarios.</p>
 *
 */
public class ItemCarrito implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Producto asociado a este ítem. */
    private Producto producto;

    /** Cantidad del producto agregada al carrito. */
    private int cantidad;

    /**
     * Constructor vacío requerido por frameworks, serialización o inicialización manual.
     */
    public ItemCarrito() {
    }

    /**
     * Constructor que permite inicializar el ítem con un producto y una cantidad específica.
     *
     * @param producto el producto asociado al ítem.
     * @param cantidad la cantidad seleccionada del producto.
     */
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Establece el producto del ítem.
     *
     * @param producto el producto a asignar.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Establece la cantidad del producto.
     *
     * @param cantidad la cantidad a asignar.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto asociado al ítem.
     *
     * @return el producto.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene la cantidad del producto.
     *
     * @return la cantidad.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Calcula el subtotal del ítem (precio del producto multiplicado por la cantidad).
     *
     * @return el valor subtotal de este ítem.
     */
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    /**
     * Devuelve una representación textual del ítem del carrito.
     *
     * @return una cadena con el producto, cantidad y subtotal.
     */
    @Override
    public String toString() {
        return producto.toString() + " x " + cantidad + " = $" + getSubtotal();
    }
}

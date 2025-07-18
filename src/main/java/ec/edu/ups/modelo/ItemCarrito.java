package ec.edu.ups.modelo;

/**
 * Clase que representa un ítem dentro de un carrito de compras.
 * Contiene un producto y la cantidad seleccionada de ese producto.
 */
public class ItemCarrito {

    private Producto producto;
    private int cantidad;

    /**
     * Constructor vacío.
     * Requerido para ciertos frameworks o inicialización manual.
     */
    public ItemCarrito() {
    }

    /**
     * Constructor que inicializa un ítem con un producto y su cantidad.
     * @param producto el producto asociado al ítem.
     * @param cantidad la cantidad seleccionada del producto.
     */
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Establece el producto del ítem.
     * @param producto el producto a asignar.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Establece la cantidad del producto.
     * @param cantidad la cantidad a asignar.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto asociado al ítem.
     * @return el producto.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene la cantidad del producto.
     * @return la cantidad.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Calcula el subtotal del ítem (precio del producto por cantidad).
     * @return el valor subtotal.
     */
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    /**
     * Representación en cadena del ítem del carrito.
     * @return una cadena que incluye el producto, la cantidad y el subtotal.
     */
    @Override
    public String toString() {
        return producto.toString() + " x " + cantidad + " = $" + getSubtotal();
    }
}

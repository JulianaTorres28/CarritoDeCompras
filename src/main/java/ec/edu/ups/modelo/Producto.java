package ec.edu.ups.modelo;

/**
 * Clase que representa un producto disponible en el sistema.
 * Contiene información como el código, nombre y precio del producto.
 */
public class Producto {

    private int codigo;
    private String nombre;
    private double precio;

    /**
     * Constructor vacío para permitir la creación de un objeto Producto sin inicializar sus atributos.
     */
    public Producto() {
    }

    /**
     * Constructor que permite inicializar todos los atributos del producto.
     *
     * @param codigo el código único del producto
     * @param nombre el nombre del producto
     * @param precio el precio del producto
     */
    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Establece el código del producto.
     *
     * @param codigo el nuevo código
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio el nuevo precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Devuelve el código del producto.
     *
     * @return el código
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Devuelve el nombre del producto.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el precio del producto.
     *
     * @return el precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Retorna una representación en cadena del producto con su código, nombre y precio.
     *
     * @return una cadena con la información del producto
     */
    @Override
    public String toString() {
        return codigo + " - " + nombre + " - $" + precio;
    }
}

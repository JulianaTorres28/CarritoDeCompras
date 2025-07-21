package ec.edu.ups.modelo;

import java.io.Serializable;

/**
 * Clase que representa un producto disponible en el sistema.
 * <p>
 * Cada producto cuenta con un código único, un nombre descriptivo y un precio.
 * Esta clase es fundamental para las operaciones de venta, inventario y carrito de compras.
 * </p>
 *
 * <p>Implementa {@link Serializable} para permitir su almacenamiento en archivos binarios o transferencia en redes.</p>
 *
 */
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Código único del producto. */
    private int codigo;

    /** Nombre o descripción del producto. */
    private String nombre;

    /** Precio del producto. */
    private double precio;

    /**
     * Constructor por defecto.
     * <p>
     * Necesario para la deserialización o cuando se desea instanciar sin asignar valores iniciales.
     * </p>
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
     * @param codigo nuevo código del producto
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre nuevo nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio nuevo precio del producto
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el código del producto.
     *
     * @return código del producto
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return precio del producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Retorna una representación textual del producto.
     * Formato: {@code código - nombre - $precio}
     *
     * @return cadena representativa del producto
     */
    @Override
    public String toString() {
        return codigo + " - " + nombre + " - $" + precio;
    }
}

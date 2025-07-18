package ec.edu.ups.dao;

import ec.edu.ups.modelo.Producto;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para los productos.
 */
public interface ProductoDAO {

    /**
     * Crea o guarda un nuevo producto en el sistema.
     *
     * @param producto El producto a guardar.
     */
    void crear(Producto producto);

    /**
     * Busca un producto por su código único.
     *
     * @param codigo Código del producto a buscar.
     * @return El producto encontrado o null si no existe.
     */
    Producto buscarPorCodigo(int codigo);

    /**
     * Busca productos cuyo nombre comience con el valor proporcionado.
     *
     * @param nombre Nombre o prefijo del nombre a buscar.
     * @return Lista de productos que coinciden con el nombre.
     */
    List<Producto> buscarPorNombre(String nombre);

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param producto El producto con los datos actualizados.
     */
    void actualizar(Producto producto);

    /**
     * Elimina un producto del sistema según su código.
     *
     * @param codigo Código del producto a eliminar.
     */
    void eliminar(int codigo);

    /**
     * Lista todos los productos registrados en el sistema.
     *
     * @return Lista completa de productos.
     */
    List<Producto> listarTodos();
}

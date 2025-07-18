package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementación en memoria de la interfaz ProductoDAO.
 * Esta clase permite almacenar, buscar, actualizar y eliminar productos sin el uso de una base de datos.
 */
public class ProductoDAOMemoria implements ProductoDAO {

    private List<Producto> productos;

    /**
     * Constructor que inicializa la lista de productos en memoria.
     */
    public ProductoDAOMemoria() {
        productos = new ArrayList<>();
    }

    /**
     * Guarda un nuevo producto en la lista.
     *
     * @param producto Producto a agregar.
     */
    @Override
    public void crear(Producto producto) {
        productos.add(producto);
    }

    /**
     * Busca un producto por su código.
     *
     * @param codigo Código del producto.
     * @return El producto si se encuentra, o null si no.
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    /**
     * Busca productos cuyo nombre comienza con la cadena dada.
     *
     * @param nombre Nombre o prefijo del nombre del producto.
     * @return Lista de productos que coinciden.
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().startsWith(nombre)) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    /**
     * Actualiza los datos de un producto ya existente.
     *
     * @param producto Producto con la información actualizada.
     */
    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
                break;
            }
        }
    }

    /**
     * Elimina un producto según su código.
     *
     * @param codigo Código del producto a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    /**
     * Lista todos los productos almacenados.
     *
     * @return Lista completa de productos.
     */
    @Override
    public List<Producto> listarTodos() {
        return productos;
    }
}

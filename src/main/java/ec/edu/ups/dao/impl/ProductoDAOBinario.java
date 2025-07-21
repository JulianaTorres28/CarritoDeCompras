package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link ProductoDAO} utilizando archivos binarios.
 * Esta clase permite persistir y recuperar objetos de tipo {@link Producto} desde un archivo .dat.
 */
public class ProductoDAOBinario implements ProductoDAO {

    /** Ruta del archivo binario donde se almacenan los productos. */
    private final String archivo;

    /**
     * Constructor que inicializa el DAO con una ruta de archivo.
     *
     * @param archivo Ruta del archivo binario donde se guardan los productos.
     */
    public ProductoDAOBinario(String archivo) {
        this.archivo = archivo;
    }

    /**
     * Guarda un nuevo producto en el archivo binario.
     *
     * @param producto Producto a agregar.
     */
    @Override
    public void crear(Producto producto) {
        List<Producto> productos = listarTodos();
        productos.add(producto);
        guardarLista(productos);
    }

    /**
     * Busca un producto por su código.
     *
     * @param codigo Código del producto.
     * @return El producto si se encuentra, o null si no existe.
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : listarTodos()) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    /**
     * Busca productos que coincidan con el nombre (inicia con).
     *
     * @param nombre Nombre parcial o completo del producto.
     * @return Lista de productos que coincidan con el criterio.
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto p : listarTodos()) {
            if (p.getNombre().startsWith(nombre)) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    /**
     * Actualiza un producto existente en el archivo.
     *
     * @param producto Producto con los datos actualizados.
     */
    @Override
    public void actualizar(Producto producto) {
        List<Producto> productos = listarTodos();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
                break;
            }
        }
        guardarLista(productos);
    }

    /**
     * Elimina un producto por su código.
     *
     * @param codigo Código del producto a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        List<Producto> productos = listarTodos();
        productos.removeIf(p -> p.getCodigo() == codigo);
        guardarLista(productos);
    }

    /**
     * Lista todos los productos almacenados en el archivo binario.
     *
     * @return Lista de productos.
     */
    @Override
    public List<Producto> listarTodos() {
        File file = new File(archivo);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Producto>) ois.readObject();
        } catch (EOFException eof) {
            // Archivo vacío
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /**
     * Guarda la lista de productos en el archivo binario.
     *
     * @param productos Lista de productos a guardar.
     */
    private void guardarLista(List<Producto> productos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

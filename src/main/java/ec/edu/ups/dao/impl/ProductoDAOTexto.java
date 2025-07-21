package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link ProductoDAO} que utiliza archivos de texto para almacenar productos.
 * Cada línea del archivo representa un producto en formato: codigo;nombre;precio
 */
public class ProductoDAOTexto implements ProductoDAO {

    /** Nombre del archivo donde se almacenan los productos en texto plano. */
    private final String archivo = "productos.txt";

    /**
     * Agrega un nuevo producto al archivo.
     *
     * @param producto Producto a guardar.
     */
    @Override
    public void crear(Producto producto) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(producto.getCodigo() + ";" + producto.getNombre() + ";" + producto.getPrecio());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca un producto por su código único.
     *
     * @param codigo Código del producto.
     * @return Producto si se encuentra, o null si no existe.
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        List<Producto> productos = listarTodos();
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    /**
     * Busca productos cuyo nombre comience con el valor dado.
     *
     * @param nombre Nombre o parte inicial del nombre del producto.
     * @return Lista de productos encontrados.
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto producto : listarTodos()) {
            if (producto.getNombre().startsWith(nombre)) {
                encontrados.add(producto);
            }
        }
        return encontrados;
    }

    /**
     * Actualiza la información de un producto existente.
     *
     * @param producto Producto con los datos actualizados.
     */
    @Override
    public void actualizar(Producto producto) {
        List<Producto> productos = listarTodos();
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Producto p : productos) {
                if (p.getCodigo() == producto.getCodigo()) {
                    pw.println(producto.getCodigo() + ";" + producto.getNombre() + ";" + producto.getPrecio());
                } else {
                    pw.println(p.getCodigo() + ";" + p.getNombre() + ";" + p.getPrecio());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un producto por su código del archivo de texto.
     *
     * @param codigo Código del producto a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        List<Producto> productos = listarTodos();
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Producto p : productos) {
                if (p.getCodigo() != codigo) {
                    pw.println(p.getCodigo() + ";" + p.getNombre() + ";" + p.getPrecio());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todos los productos almacenados en el archivo de texto.
     *
     * @return Lista de productos disponibles.
     */
    @Override
    public List<Producto> listarTodos() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    productos.add(new Producto(codigo, nombre, precio));
                }
            }
        } catch (IOException e) {
        }
        return productos;
    }
}

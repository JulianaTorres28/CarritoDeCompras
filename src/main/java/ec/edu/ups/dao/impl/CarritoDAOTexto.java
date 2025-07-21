package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz CarritoDAO que utiliza almacenamiento en archivos de texto.
 * Guarda y recupera información básica del carrito (código y usuario), sin los ítems.
 */
public class CarritoDAOTexto implements CarritoDAO {

    /** Ruta del archivo de texto donde se almacenan los carritos. */
    private String archivo;

    /**
     * Constructor que inicializa el archivo donde se almacenarán los datos.
     * @param archivo Ruta del archivo.
     */
    public CarritoDAOTexto(String archivo) {
        this.archivo = archivo;
    }

    /**
     * Crea un nuevo carrito y lo guarda en el archivo.
     * @param carrito Objeto Carrito a almacenar.
     */
    @Override
    public void crear(Carrito carrito) {
        List<Carrito> carritos = listarTodos();
        carritos.add(carrito);
        guardarEnArchivo(carritos);
    }

    /**
     * Busca un carrito por su código único.
     * @param codigo Código del carrito.
     * @return Carrito encontrado o null si no existe.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito carrito : listarTodos()) {
            if (carrito.getCodigo() == codigo) {
                return carrito;
            }
        }
        return null;
    }

    /**
     * Actualiza los datos de un carrito existente.
     * @param carrito Carrito con los datos actualizados.
     */
    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> carritos = listarTodos();
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
        guardarEnArchivo(carritos);
    }

    /**
     * Elimina un carrito del archivo según su código.
     * @param codigo Código del carrito a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = listarTodos();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarEnArchivo(carritos);
    }

    /**
     * Lista todos los carritos almacenados en el archivo.
     * Solo recupera código y username del usuario.
     * @return Lista de carritos.
     */
    @Override
    public List<Carrito> listarTodos() {
        List<Carrito> carritos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                Carrito carrito = new Carrito();
                carrito.setCodigo(Integer.parseInt(datos[0]));
                Usuario usuario = new Usuario();
                usuario.setUsername(datos[1]);
                carrito.setUsuario(usuario);
                carritos.add(carrito);
            }
        } catch (IOException e) {
            // Archivo no existe o está vacío, lo ignoramos
        }
        return carritos;
    }

    /**
     * Busca todos los carritos asociados a un usuario específico.
     * @param usuario Usuario del que se quieren obtener los carritos.
     * @return Lista de carritos pertenecientes al usuario.
     */
    @Override
    public List<Carrito> buscarPorUsuario(Usuario usuario) {
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito carrito : listarTodos()) {
            if (carrito.getUsuario() != null &&
                    carrito.getUsuario().getUsername().equals(usuario.getUsername())) {
                resultado.add(carrito);
            }
        }
        return resultado;
    }

    /**
     * Guarda todos los carritos en el archivo, sobrescribiendo el contenido anterior.
     * @param carritos Lista completa de carritos a escribir en el archivo.
     */
    private void guardarEnArchivo(List<Carrito> carritos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Carrito carrito : carritos) {
                String linea = carrito.getCodigo() + ";" +
                        (carrito.getUsuario() != null ? carrito.getUsuario().getUsername() : "null");
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

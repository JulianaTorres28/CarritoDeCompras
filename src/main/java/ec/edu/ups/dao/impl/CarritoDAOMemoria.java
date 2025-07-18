package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementación en memoria de la interfaz CarritoDAO.
 * Permite realizar operaciones CRUD sobre objetos Carrito sin usar una base de datos.
 */
public class CarritoDAOMemoria implements CarritoDAO {

    private List<Carrito> carritos;

    /**
     * Constructor que inicializa la lista de carritos.
     */
    public CarritoDAOMemoria() {
        this.carritos = new ArrayList<>();
    }

    /**
     * Agrega un nuevo carrito a la lista.
     *
     * @param carrito Objeto Carrito a crear.
     */
    @Override
    public void crear(Carrito carrito) {
        carritos.add(carrito);
    }

    /**
     * Busca un carrito por su código.
     *
     * @param codigo Código del carrito.
     * @return El carrito si se encuentra, o null si no existe.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito carrito : carritos) {
            if (carrito.getCodigo() == codigo) {
                return carrito;
            }
        }
        return null;
    }

    /**
     * Actualiza un carrito existente en la lista.
     *
     * @param carrito Objeto Carrito con los datos actualizados.
     */
    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
    }

    /**
     * Elimina un carrito de la lista según su código.
     *
     * @param codigo Código del carrito a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        Iterator<Carrito> iterator = carritos.iterator();
        while (iterator.hasNext()) {
            Carrito carrito = iterator.next();
            if (carrito.getCodigo() == codigo) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Retorna todos los carritos almacenados en memoria.
     *
     * @return Lista de carritos.
     */
    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }

    /**
     * Busca todos los carritos que pertenecen a un usuario.
     *
     * @param usuario Usuario al que pertenecen los carritos.
     * @return Lista de carritos asociados al usuario.
     */
    @Override
    public List<Carrito> buscarPorUsuario(Usuario usuario) {
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito carrito : carritos) {
            if (carrito.getUsuario() != null &&
                    carrito.getUsuario().getUsername().equals(usuario.getUsername())) {
                resultado.add(carrito);
            }
        }
        return resultado;
    }
}

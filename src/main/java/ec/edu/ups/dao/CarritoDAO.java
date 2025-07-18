package ec.edu.ups.dao;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad {@link Carrito}.
 * Provee métodos para gestionar carritos de compras en el sistema.
 */
public interface CarritoDAO {

    /**
     * Crea un nuevo carrito y lo guarda en la fuente de datos.
     *
     * @param carrito Objeto {@link Carrito} a crear.
     */
    void crear(Carrito carrito);

    /**
     * Busca un carrito por su código único.
     *
     * @param codigo Código del carrito.
     * @return Carrito encontrado o null si no existe.
     */
    Carrito buscarPorCodigo(int codigo);

    /**
     * Actualiza los datos de un carrito existente.
     *
     * @param carrito Carrito con los datos actualizados.
     */
    void actualizar(Carrito carrito);

    /**
     * Elimina un carrito según su código.
     *
     * @param codigo Código del carrito a eliminar.
     */
    void eliminar(int codigo);

    /**
     * Lista todos los carritos registrados en el sistema.
     *
     * @return Lista de todos los carritos.
     */
    List<Carrito> listarTodos();

    /**
     * Busca todos los carritos asociados a un usuario específico.
     *
     * @param usuario Usuario del que se desean obtener los carritos.
     * @return Lista de carritos del usuario.
     */
    List<Carrito> buscarPorUsuario(Usuario usuario);
}

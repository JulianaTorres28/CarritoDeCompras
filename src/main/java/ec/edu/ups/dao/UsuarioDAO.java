package ec.edu.ups.dao;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para los usuarios del sistema.
 */
public interface UsuarioDAO {

    /**
     * Autentica a un usuario mediante su nombre de usuario y contraseña.
     *
     * @param username Nombre de usuario.
     * @param contrasenia Contraseña del usuario.
     * @return El usuario autenticado o null si no se encuentra o no coincide.
     */
    Usuario autenticar(String username, String contrasenia);

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuario El objeto Usuario a registrar.
     */
    void crear(Usuario usuario);

    /**
     * Busca un usuario en el sistema por su nombre de usuario.
     *
     * @param username El nombre de usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    Usuario buscarPorUsername(String username);

    /**
     * Elimina un usuario del sistema según su nombre de usuario.
     *
     * @param username El nombre de usuario a eliminar.
     */
    void eliminar(String username);

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuario El objeto Usuario con los datos actualizados.
     */
    void actualizar(Usuario usuario);

    /**
     * Lista todos los usuarios registrados en el sistema.
     *
     * @return Una lista de todos los usuarios.
     */
    List<Usuario> listarTodos();

    /**
     * Lista todos los usuarios que tienen un rol específico.
     *
     * @param rol El rol por el que se desea filtrar.
     * @return Lista de usuarios que pertenecen al rol especificado.
     */
    List<Usuario> listarPorRol(Rol rol);
}

package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementación en memoria de la interfaz UsuarioDAO.
 * Esta clase permite gestionar objetos de tipo Usuario sin el uso de una base de datos.
 */
public class UsuarioDAOMemoria implements UsuarioDAO {

    private List<Usuario> usuarios;

    /**
     * Constructor que inicializa la lista de usuarios.
     */
    public UsuarioDAOMemoria() {
        usuarios = new ArrayList<>();
    }

    /**
     * Autentica un usuario verificando su nombre de usuario y contraseña.
     *
     * @param username     Nombre de usuario.
     * @param contrasenia  Contraseña.
     * @return El usuario autenticado si las credenciales son correctas, o null si no lo son.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) &&
                    usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Crea un nuevo usuario y lo agrega a la lista.
     *
     * @param usuario Usuario a agregar.
     */
    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getUsername().equals(username)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param usuario Usuario con datos actualizados.
     */
    @Override
    public void actualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuarioAux = usuarios.get(i);
            if (usuarioAux.getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
    }

    /**
     * Retorna la lista completa de usuarios almacenados.
     *
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    /**
     * Lista los usuarios que tienen un rol específico.
     *
     * @param rol Rol a buscar.
     * @return Lista de usuarios con ese rol.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> usuariosEncontrados = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getRol().equals(rol)) {
                usuariosEncontrados.add(usuario);
            }
        }

        return usuariosEncontrados;
    }
}

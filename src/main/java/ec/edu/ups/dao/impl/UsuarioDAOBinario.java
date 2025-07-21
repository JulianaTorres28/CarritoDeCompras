package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de {@link UsuarioDAO} que utiliza archivos binarios para almacenar objetos {@link Usuario}.
 * La lista de usuarios se mantiene en memoria y se sincroniza con el archivo en cada operación de modificación.
 */
public class UsuarioDAOBinario implements UsuarioDAO {

    /** Ruta del archivo binario donde se almacenan los usuarios. */
    private final String archivo;

    /** Lista de usuarios cargada desde el archivo. */
    private List<Usuario> usuarios;

    /**
     * Constructor que inicializa el DAO con la ruta del archivo y carga los usuarios existentes.
     *
     * @param archivo Ruta del archivo binario.
     */
    public UsuarioDAOBinario(String archivo) {
        this.archivo = archivo;
        this.usuarios = cargarUsuarios();
    }

    /**
     * Autentica un usuario comparando nombre de usuario y contraseña.
     *
     * @param username    Nombre de usuario.
     * @param contrasenia Contraseña.
     * @return Usuario autenticado si las credenciales coinciden, o null si no se encuentra.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Crea un nuevo usuario y lo guarda en el archivo.
     *
     * @param usuario Usuario a agregar.
     */
    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
        guardarUsuarios();
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Usuario encontrado o null si no existe.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario según su nombre de usuario.
     *
     * @param username Nombre de usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        usuarios.removeIf(u -> u.getUsername().equals(username));
        guardarUsuarios();
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuario Usuario actualizado.
     */
    @Override
    public void actualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        guardarUsuarios();
    }

    /**
     * Devuelve la lista de todos los usuarios almacenados.
     *
     * @return Lista completa de usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    /**
     * Lista los usuarios que tienen un rol específico.
     *
     * @param rol Rol a filtrar.
     * @return Lista de usuarios con el rol indicado.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> result = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getRol().equals(rol)) {
                result.add(u);
            }
        }
        return result;
    }

    /**
     * Guarda todos los usuarios en el archivo binario.
     */
    private void guardarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la lista de usuarios desde el archivo binario.
     *
     * @return Lista de usuarios, o una lista vacía si el archivo no existe o está vacío.
     */
    private List<Usuario> cargarUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}

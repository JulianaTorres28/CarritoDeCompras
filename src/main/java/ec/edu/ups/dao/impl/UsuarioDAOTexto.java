package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de {@link UsuarioDAO} que almacena los usuarios en un archivo de texto.
 * Cada línea del archivo representa un usuario en formato separado por punto y coma.
 */
public class UsuarioDAOTexto implements UsuarioDAO {

    /** Ruta del archivo donde se almacenan los usuarios. */
    private static final String FILE_PATH = "usuarios.txt";

    /** Lista de usuarios cargada desde el archivo. */
    private List<Usuario> usuarios;

    /**
     * Constructor que carga la lista de usuarios desde el archivo de texto.
     */
    public UsuarioDAOTexto() {
        usuarios = cargarUsuarios();
    }

    /**
     * Autentica a un usuario comparando su nombre de usuario y contraseña.
     *
     * @param username    Nombre de usuario.
     * @param contrasenia Contraseña.
     * @return El {@link Usuario} autenticado o null si no coincide.
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
     * @param usuario El usuario a registrar.
     */
    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
        guardarUsuarios();
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar.
     * @return El usuario encontrado o null si no existe.
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
     * Elimina un usuario de la lista y actualiza el archivo.
     *
     * @param username Nombre de usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        usuarios.removeIf(u -> u.getUsername().equals(username));
        guardarUsuarios();
    }

    /**
     * Actualiza los datos de un usuario existente.
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
     * Lista todos los usuarios registrados.
     *
     * @return Lista completa de usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    /**
     * Filtra los usuarios según su rol.
     *
     * @param rol Rol de usuario a buscar.
     * @return Lista de usuarios con el rol especificado.
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
     * Guarda la lista actual de usuarios en el archivo de texto.
     */
    private void guardarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Usuario u : usuarios) {
                writer.write(u.getUsername() + ";" + u.getContrasenia() + ";" + u.getCedula() + ";" + u.getRol().name());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga los usuarios desde el archivo de texto.
     *
     * @return Lista de usuarios cargados o vacía si el archivo no existe.
     */
    private List<Usuario> cargarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 4) {
                    Usuario usuario = new Usuario();
                    usuario.setUsername(partes[0]);
                    usuario.setContrasenia(partes[1]);
                    usuario.setCedula(partes[2]);
                    usuario.setRol(Rol.valueOf(partes[3]));
                    lista.add(usuario);
                }
            }
        } catch (IOException e) {
        }
        return lista;
    }
}

package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO de Usuario usando archivos de texto como medio de persistencia.
 * Cada usuario se almacena en una línea del archivo, con campos separados por punto y coma (;).
 */
public class UsuarioDAOTexto implements UsuarioDAO {

    private final File archivo;

    /**
     * Constructor que inicializa el archivo a utilizar.
     *
     * @param ruta Ruta del archivo donde se almacenarán los datos de usuario.
     */
    public UsuarioDAOTexto(String ruta) {
        this.archivo = new File(ruta);
    }

    /**
     * Autentica un usuario verificando su nombre de usuario y contraseña.
     *
     * @param username     Nombre de usuario.
     * @param contrasenia  Contraseña.
     * @return El usuario autenticado si las credenciales coinciden, o null si no existe o son incorrectas.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 4) {
                    String cedula = datos[0];
                    String user = datos[1];
                    String pass = datos[2];
                    String rol = datos[3];

                    if (user.equals(username) && pass.equals(contrasenia)) {
                        return new Usuario(cedula, user, pass, Rol.valueOf(rol));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Crea un nuevo usuario y lo agrega al archivo de texto.
     *
     * @param usuario Usuario a registrar.
     */
    @Override
    public void crear(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            String linea = usuario.getCedula() + ";" +
                    usuario.getUsername() + ";" +
                    usuario.getContrasenia() + ";" +
                    usuario.getRol().name();
            writer.write(linea);
            writer.newLine();
            System.out.println("Guardado en archivo: " + linea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca un usuario en el archivo por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Usuario encontrado o null si no existe.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario u : listarTodos()) {
            if (u.getUsername().trim().equals(username.trim())) {
                return u;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario del archivo por su nombre de usuario.
     *
     * @param username Nombre de usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listarTodos();
        usuarios.removeIf(u -> u.getUsername().trim().equals(username.trim()));
        sobreescribirArchivo(usuarios);
    }

    /**
     * Actualiza los datos de un usuario existente en el archivo.
     *
     * @param usuario Usuario actualizado.
     */
    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().trim().equals(usuario.getUsername().trim())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        sobreescribirArchivo(usuarios);
    }

    /**
     * Lista todos los usuarios almacenados en el archivo.
     *
     * @return Lista de todos los usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        if (!archivo.exists()) return lista;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String cedula = partes[0].trim();
                    String username = partes[1].trim();
                    String contrasenia = partes[2].trim();
                    Rol rol = Rol.valueOf(partes[3].trim());

                    Usuario usuario = new Usuario(cedula, username, contrasenia, rol);
                    lista.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Lista todos los usuarios que tienen un rol específico.
     *
     * @param rol Rol a filtrar.
     * @return Lista de usuarios con ese rol.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> lista = new ArrayList<>();
        for (Usuario u : listarTodos()) {
            if (u.getRol().equals(rol)) {
                lista.add(u);
            }
        }
        return lista;
    }

    /**
     * Sobrescribe el archivo con la lista de usuarios proporcionada.
     *
     * @param usuarios Lista de usuarios que reemplazará el contenido del archivo.
     */
    private void sobreescribirArchivo(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Usuario u : usuarios) {
                writer.write(u.getCedula().trim() + ";" +
                        u.getUsername().trim() + ";" +
                        u.getContrasenia().trim() + ";" +
                        u.getRol().name().trim());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

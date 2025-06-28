package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.LoginView;
import ec.edu.ups.vista.UsuarioAdminView;
import ec.edu.ups.vista.UsuarioRegistroView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UsuarioController {

    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private final UsuarioRegistroView registroView;
    private final UsuarioAdminView usuarioAdminView;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, UsuarioRegistroView registroView, UsuarioAdminView usuarioAdminView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.registroView = registroView;
        this.usuario = null;
        this.usuarioAdminView = usuarioAdminView;
        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas(){
        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });

        loginView.getBtnRegistrarse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioRegistro();
            }
        });

        registroView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        if (usuarioAdminView != null) {
            if (usuarioAdminView.getBtnBuscar() != null) {
                usuarioAdminView.getBtnBuscar().addActionListener(e -> buscarUsuarios());
            }
            if (usuarioAdminView.getBtnActualizar() != null) {
                usuarioAdminView.getBtnActualizar().addActionListener(e -> actualizarUsuario());
            }
            if (usuarioAdminView.getBtnEliminar() != null) {
                usuarioAdminView.getBtnEliminar().addActionListener(e -> eliminarUsuario());
            }
            if (usuarioAdminView.getBtnEditar() != null) {
                usuarioAdminView.getBtnEditar().addActionListener(e -> editarUsuario());
            }
        }
    }

    private void autenticar(){
        String username = loginView.getTxtUsername().getText();
        String contrasenia = loginView.getTxtContrasenia().getText();

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if(usuario == null){
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        } else {
            loginView.dispose();
        }
    }

    private void mostrarFormularioRegistro() {
        registroView.setVisible(true);
    }

    private void registrarUsuario() {
        String username = registroView.getTxtUsername().getText();
        String contrasenia = new String(registroView.getTxtContrasenia().getPassword());
        Rol rol = (Rol) registroView.getCbxRol().getSelectedItem();

        if (usuarioDAO.buscarPorUsername(username) != null) {
            registroView.mostrarMensaje("Ese usuario ya existe. Intenta con otro.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(username, contrasenia, rol);
        usuarioDAO.crear(nuevoUsuario);
        registroView.mostrarMensaje("Usuario registrado exitosamente.");
        registroView.dispose();
    }

    public Usuario getUsuarioAutenticado(){
        return usuario;
    }

    public void buscarUsuarios() {
        String username = usuarioAdminView.getTxtusername().getText();

        if (username != null && !username.trim().isEmpty()) {
            Usuario usuario = usuarioDAO.buscarPorUsername(username);
            if (usuario != null) {
                List<Usuario> listaUsuario = List.of(usuario);
                usuarioAdminView.cargarDatos(listaUsuario);
            } else {
                usuarioAdminView.mostrarMensaje("No se encontró el usuario: " + username);
                List<Usuario> todos = usuarioDAO.listarTodos();
                usuarioAdminView.cargarDatos(todos);
            }
        } else {
            List<Usuario> lista = usuarioDAO.listarTodos();
            usuarioAdminView.cargarDatos(lista);
        }
    }

    private void actualizarUsuario() {
        String username = usuarioAdminView.getTxtusername().getText();
        String contrasenia = usuarioAdminView.getTxtcontrasenia().getText();
        Rol rol = (Rol) usuarioAdminView.getCbxRol().getSelectedItem();

        Usuario usuario = new Usuario(username, contrasenia, rol);
        usuarioDAO.actualizar(usuario);

        usuarioAdminView.mostrarMensaje("Usuario actualizado correctamente.");
        buscarUsuarios();
    }

    private void eliminarUsuario() {
        String username = usuarioAdminView.getTxtusername().getText();
        usuarioDAO.eliminar(username);

        usuarioAdminView.mostrarMensaje("Usuario eliminado correctamente.");
        buscarUsuarios();
    }

    private void editarUsuario() {
        int fila = usuarioAdminView.getTblUsuarios().getSelectedRow();
        if (fila >= 0) {
            String username = usuarioAdminView.getTblUsuarios().getValueAt(fila, 0).toString();
            Usuario usuario = usuarioDAO.buscarPorUsername(username);
            usuarioAdminView.getTxtusername().setText(usuario.getUsername());
            usuarioAdminView.getTxtcontrasenia().setText(usuario.getContrasenia());
            usuarioAdminView.getCbxRol().setSelectedItem(usuario.getRol());
        } else {
            usuarioAdminView.mostrarMensaje("Seleccione un usuario para editar.");
        }
    }


}
package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntaSeguridadDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.PreguntaSeguridad;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.LoginView;
import ec.edu.ups.vista.RecuperarCuentaView;
import ec.edu.ups.vista.UsuarioAdminView;
import ec.edu.ups.vista.UsuarioRegistroView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private Usuario usuarioAutenticado;
    private Usuario usuario;

    private final UsuarioDAO usuarioDAO;
    private final PreguntaSeguridadDAO preguntaDAO;

    private final LoginView loginView;
    private final UsuarioRegistroView registroView;
    private final UsuarioAdminView usuarioAdminView;
    private final RecuperarCuentaView recuperarCuentaView;

    private List<PreguntaSeguridad> preguntasSeleccionadas;

    public UsuarioController(
            UsuarioDAO usuarioDAO,
            PreguntaSeguridadDAO preguntaDAO,
            LoginView loginView,
            UsuarioRegistroView registroView,
            UsuarioAdminView usuarioAdminView,
            RecuperarCuentaView recuperarCuentaView
    ) {
        this.usuarioDAO = usuarioDAO;
        this.preguntaDAO = preguntaDAO;

        this.loginView = loginView;
        this.registroView = registroView;
        this.usuarioAdminView = usuarioAdminView;
        this.recuperarCuentaView = recuperarCuentaView;

        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
        loginView.getBtnIniciarSesion().addActionListener(e -> autenticar());
        loginView.getBtnRegistrarse().addActionListener(e -> mostrarFormularioRegistro());
        registroView.getBtnGuardar().addActionListener(e -> registrarUsuario());
        loginView.getBtnRecuperar().addActionListener(e -> recuperarCuentaView.setVisible(true));

        if (usuarioAdminView != null) {
            if (usuarioAdminView.getBtnBuscar() != null) usuarioAdminView.getBtnBuscar().addActionListener(e -> buscarUsuarios());
            if (usuarioAdminView.getBtnActualizar() != null) usuarioAdminView.getBtnActualizar().addActionListener(e -> actualizarUsuario());
            if (usuarioAdminView.getBtnEliminar() != null) usuarioAdminView.getBtnEliminar().addActionListener(e -> eliminarUsuario());
            if (usuarioAdminView.getBtnEditar() != null) usuarioAdminView.getBtnEditar().addActionListener(e -> editarUsuario());
        }

        recuperarCuentaView.getBtnVerificarUsuario().addActionListener(e -> verificarUsuario());
        recuperarCuentaView.getBtnVerificarRespuestas().addActionListener(e -> verificarRespuestas());
        recuperarCuentaView.getBtnGuardarNuevaContrasenia().addActionListener(e -> guardarNuevaContrasenia());


    }

    private void autenticar() {
        String username = loginView.getTxtUsername().getText();
        String contrasenia = loginView.getTxtContrasenia().getText();

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if (usuario == null) {
            loginView.mostrarMensaje("login.mensaje.error");
        } else {
            usuarioAutenticado = usuario;
            loginView.dispose();
        }
    }

    private void mostrarFormularioRegistro() {
        List<PreguntaSeguridad> preguntasBase = preguntaDAO.obtenerPreguntasBase();
        registroView.setPreguntasBase(preguntasBase);

        registroView.actualizarTextos();

        registroView.setVisible(true);
    }



    private void registrarUsuario() {
        String username = registroView.getTxtUsername().getText().trim();
        String contrasenia = new String(registroView.getTxtContrasenia().getPassword()).trim();
        Rol rol = (Rol) registroView.getCbxRol().getSelectedItem();

        String pregunta1 = (String) registroView.getCbxPregunta1().getSelectedItem();
        String respuesta1 = registroView.getTxtRespuesta1().getText().trim();
        String pregunta2 = (String) registroView.getCbxPregunta2().getSelectedItem();
        String respuesta2 = registroView.getTxtRespuesta2().getText().trim();
        String pregunta3 = (String) registroView.getCbxPregunta3().getSelectedItem();
        String respuesta3 = registroView.getTxtRespuesta3().getText().trim();

        if (username.isEmpty() || contrasenia.isEmpty() || rol == null ||
                pregunta1 == null || respuesta1.isEmpty() ||
                pregunta2 == null || respuesta2.isEmpty() ||
                pregunta3 == null || respuesta3.isEmpty()) {

            registroView.mostrarMensaje("usuario.registro.campos.vacios");
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            registroView.mostrarMensaje("usuario.registro.yaexiste");
            return;
        }

        Usuario nuevoUsuario = new Usuario(username, contrasenia, rol);
        usuarioDAO.crear(nuevoUsuario);

        List<PreguntaSeguridad> preguntasUsuario = new ArrayList<>();
        preguntasUsuario.add(new PreguntaSeguridad(pregunta1, respuesta1));
        preguntasUsuario.add(new PreguntaSeguridad(pregunta2, respuesta2));
        preguntasUsuario.add(new PreguntaSeguridad(pregunta3, respuesta3));

        preguntaDAO.guardarPreguntasPorUsuario(username, preguntasUsuario);

        registroView.mostrarMensaje("usuario.registro.exito");
        registroView.dispose();
    }


    private void guardarNuevaContrasenia() {
        String username = recuperarCuentaView.getTxtUsuario().getText().trim();
        String nuevaContrasenia = recuperarCuentaView.getTxtNuevaContrasenia().getText().trim();
        if (!nuevaContrasenia.isEmpty()) {
            Usuario usuario = usuarioDAO.buscarPorUsername(username);
            if (usuario != null) {
                usuario.setContrasenia(nuevaContrasenia);
                usuarioDAO.actualizar(usuario);
                recuperarCuentaView.mostrarMensaje("recuperar.mensaje.contrasenia.actualizada");
            }
        }
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void buscarUsuarios() {
        String username = usuarioAdminView.getTxtusername().getText();

        if (username != null && !username.trim().isEmpty()) {
            Usuario usuario = usuarioDAO.buscarPorUsername(username);
            if (usuario != null) {
                usuarioAdminView.cargarDatos(List.of(usuario));
            } else {
                usuarioAdminView.mostrarMensaje("usuario.admin.noencontrado");
                usuarioAdminView.cargarDatos(usuarioDAO.listarTodos());
            }
        } else {
            usuarioAdminView.cargarDatos(usuarioDAO.listarTodos());
        }
    }

    private void actualizarUsuario() {
        String username = usuarioAdminView.getTxtusername().getText();
        String contrasenia = usuarioAdminView.getTxtcontrasenia().getText();
        Rol rol = (Rol) usuarioAdminView.getCbxRol().getSelectedItem();

        Usuario usuario = new Usuario(username, contrasenia, rol);
        usuarioDAO.actualizar(usuario);

        usuarioAdminView.mostrarMensaje("usuario.admin.actualizado");
        buscarUsuarios();
    }

    private void eliminarUsuario() {
        String username = usuarioAdminView.getTxtusername().getText();
        usuarioDAO.eliminar(username);

        usuarioAdminView.mostrarMensaje("usuario.admin.eliminado");
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

    private void verificarUsuario() {
        String username = recuperarCuentaView.getTxtUsuario().getText().trim();

        if (username.isEmpty()) {
            recuperarCuentaView.mostrarMensaje("recuperar.mensaje.usuario.vacio");
            return;
        }

        boolean existe = preguntaDAO.usuarioExiste(username);
        if (existe) {
            preguntasSeleccionadas = preguntaDAO.obtenerPreguntasAleatorias(username, 3);
            recuperarCuentaView.setPreguntas(preguntasSeleccionadas);
            recuperarCuentaView.mostrarMensaje("");
        } else {
            recuperarCuentaView.mostrarMensaje("recuperar.mensaje.usuario.noencontrado");
        }
    }


    private void verificarRespuestas() {
        int correctas = 0;

        if (preguntasSeleccionadas.get(0).getRespuesta().equalsIgnoreCase(recuperarCuentaView.getTxtRespuesta1().getText().trim())) correctas++;
        if (preguntasSeleccionadas.get(1).getRespuesta().equalsIgnoreCase(recuperarCuentaView.getTxtRespuesta2().getText().trim())) correctas++;
        if (preguntasSeleccionadas.get(2).getRespuesta().equalsIgnoreCase(recuperarCuentaView.getTxtRespuesta3().getText().trim())) correctas++;

        if (correctas >= 3) {
            recuperarCuentaView.mostrarMensaje("recuperar.mensaje.exito");
            recuperarCuentaView.mostrarCamposNuevaContrasenia();
        } else {
            recuperarCuentaView.mostrarMensaje("recuperar.mensaje.error");
        }

    }



    public void setUsuarioAutenticado(Usuario usuario) {
        this.usuarioAutenticado = usuario;
    }
}

package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.PreguntaSeguridadDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.dao.impl.PreguntaSeguridadDAOMemoria;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {

            MensajeInternacionalizacionHandler mensajeHandler = new MensajeInternacionalizacionHandler("es", "EC");

            // DAOs
            UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
            PreguntaSeguridadDAO preguntaSeguridadDAO = new PreguntaSeguridadDAOMemoria();

            // Vistas
            LoginView loginView = new LoginView(mensajeHandler);
            UsuarioRegistroView usuarioRegistroView = new UsuarioRegistroView(mensajeHandler);
            UsuarioAdminView usuarioAdminView = new UsuarioAdminView(mensajeHandler);
            RecuperarCuentaView recuperarCuentaView = new RecuperarCuentaView(mensajeHandler);

            // Controlador principal
            UsuarioController usuarioController = new UsuarioController(
                    usuarioDAO,
                    preguntaSeguridadDAO,
                    loginView,
                    usuarioRegistroView,
                    usuarioAdminView,
                    recuperarCuentaView
            );

            loginView.setVisible(true);

            loginView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                    if (usuarioAutenticado != null) {

                        ProductoDAO productoDAO = new ProductoDAOMemoria();
                        CarritoDAO carritoDAO = new CarritoDAOMemoria();

                        MenuPrincipalView principalView = new MenuPrincipalView(mensajeHandler);
                        ProductoAnadirView productoAnadirView = new ProductoAnadirView(mensajeHandler);
                        ProductoListaView productoListaView = new ProductoListaView(mensajeHandler);
                        ProductoActualizarView productoActualizarView = new ProductoActualizarView(mensajeHandler);
                        ProductoEliminarView productoEliminarView = new ProductoEliminarView(mensajeHandler);
                        CarritoAnadirView carritoAnadirView = new CarritoAnadirView(mensajeHandler);
                        CarritoListaView carritoListaView = new CarritoListaView(mensajeHandler);

                        ProductoController productoController = new ProductoController(
                                productoDAO,
                                productoAnadirView,
                                productoListaView,
                                carritoAnadirView,
                                productoActualizarView,
                                productoEliminarView
                        );

                        CarritoController carritoController = new CarritoController(
                                carritoDAO,
                                productoDAO,
                                carritoAnadirView,
                                carritoListaView,
                                usuarioAutenticado
                        );

                        String mensajeBienvenida = mensajeHandler.getFormato("app.bienvenida", usuarioAutenticado.getUsername());
                        principalView.mostrarMensaje(mensajeBienvenida);
                        if (usuarioAutenticado.getRol().equals(Rol.USUARIO)) {
                            principalView.deshabilitarMenusAdministrador();
                        }

                        principalView.getMenuItemCrearProducto().addActionListener(ev -> {
                            if (!productoAnadirView.isVisible()) {
                                principalView.getjDesktopPane().add(productoAnadirView);
                                productoAnadirView.setVisible(true);
                            }
                        });

                        principalView.getMenuItemBuscarProducto().addActionListener(ev -> {
                            if (!productoListaView.isVisible()) {
                                principalView.getjDesktopPane().add(productoListaView);
                                productoListaView.setVisible(true);
                            }
                        });

                        principalView.getMenuItemActualizarProducto().addActionListener(ev -> {
                            if (!productoActualizarView.isVisible()) {
                                principalView.getjDesktopPane().add(productoActualizarView);
                                productoActualizarView.setVisible(true);
                            }
                        });

                        principalView.getMenuItemEliminarProducto().addActionListener(ev -> {
                            if (!productoEliminarView.isVisible()) {
                                principalView.getjDesktopPane().add(productoEliminarView);
                                productoEliminarView.setVisible(true);
                            }
                        });

                        principalView.getMenuItemCrearCarrito().addActionListener(ev -> {
                            if (!carritoAnadirView.isVisible()) {
                                principalView.getjDesktopPane().add(carritoAnadirView);
                                carritoAnadirView.setVisible(true);
                            }
                        });

                        principalView.getMenuItemListarCarritos().addActionListener(ev -> {
                            if (!carritoListaView.isVisible()) {
                                principalView.getjDesktopPane().add(carritoListaView);
                                carritoListaView.setVisible(true);
                            }
                        });

                        principalView.getMenuItemGestionarUsuarios().addActionListener(ev -> {
                            if (!usuarioAdminView.isVisible()) {
                                principalView.getjDesktopPane().add(usuarioAdminView);
                                usuarioAdminView.setVisible(true);
                                usuarioController.buscarUsuarios();
                            }
                        });

                        principalView.getMenuItemCerrarSesion().addActionListener(ev -> {
                            principalView.setVisible(false);

                            usuarioController.setUsuarioAutenticado(null);

                            loginView.limpiarCampos();
                            loginView.setVisible(true);
                        });

                        principalView.getMenuItemSalir().addActionListener(ev -> {
                            System.exit(0);
                        });

                        principalView.getMenuItemIdiomaEspanol().addActionListener(ev -> {
                            mensajeHandler.setLenguaje("es", "EC");
                            principalView.cambiarIdioma("es", "EC");

                            usuarioRegistroView.setMensajeInternacionalizacion(mensajeHandler);
                            usuarioRegistroView.actualizarTextos();

                            carritoAnadirView.setMensajeInternacionalizacion(mensajeHandler);
                            carritoAnadirView.actualizarTextos();

                            carritoListaView.setMensajeInternacionalizacion(mensajeHandler);
                            carritoListaView.actualizarTextos();

                            productoActualizarView.setMensajeInternacionalizacion(mensajeHandler);
                            productoActualizarView.actualizarTextos();

                            productoAnadirView.setMensajeInternacionalizacion(mensajeHandler);
                            productoAnadirView.actualizarTextos();

                            productoEliminarView.setMensajeInternacionalizacion(mensajeHandler);
                            productoEliminarView.actualizarTextos();

                            productoListaView.setMensajeInternacionalizacion(mensajeHandler);
                            productoListaView.actualizarTextos();

                            recuperarCuentaView.setMensajeInternacionalizacion(mensajeHandler);
                            recuperarCuentaView.actualizarTextos();
                        });

                        principalView.getMenuItemIdiomaIngles().addActionListener(ev -> {
                            mensajeHandler.setLenguaje("en", "US");
                            principalView.cambiarIdioma("en", "US");

                            usuarioRegistroView.setMensajeInternacionalizacion(mensajeHandler);
                            usuarioRegistroView.actualizarTextos();

                            carritoAnadirView.setMensajeInternacionalizacion(mensajeHandler);
                            carritoAnadirView.actualizarTextos();

                            carritoListaView.setMensajeInternacionalizacion(mensajeHandler);
                            carritoListaView.actualizarTextos();

                            productoActualizarView.setMensajeInternacionalizacion(mensajeHandler);
                            productoActualizarView.actualizarTextos();

                            productoAnadirView.setMensajeInternacionalizacion(mensajeHandler);
                            productoAnadirView.actualizarTextos();

                            productoEliminarView.setMensajeInternacionalizacion(mensajeHandler);
                            productoEliminarView.actualizarTextos();

                            productoListaView.setMensajeInternacionalizacion(mensajeHandler);
                            productoListaView.actualizarTextos();

                            recuperarCuentaView.setMensajeInternacionalizacion(mensajeHandler);
                            recuperarCuentaView.actualizarTextos();
                        });


                        principalView.getMenuItemIdiomaFrances().addActionListener(ev -> {
                            mensajeHandler.setLenguaje("fr", "FR");
                            principalView.cambiarIdioma("fr", "FR");

                            usuarioRegistroView.setMensajeInternacionalizacion(mensajeHandler);
                            usuarioRegistroView.actualizarTextos();

                            carritoAnadirView.setMensajeInternacionalizacion(mensajeHandler);
                            carritoAnadirView.actualizarTextos();

                            carritoListaView.setMensajeInternacionalizacion(mensajeHandler);
                            carritoListaView.actualizarTextos();

                            productoActualizarView.setMensajeInternacionalizacion(mensajeHandler);
                            productoActualizarView.actualizarTextos();

                            productoAnadirView.setMensajeInternacionalizacion(mensajeHandler);
                            productoAnadirView.actualizarTextos();

                            productoEliminarView.setMensajeInternacionalizacion(mensajeHandler);
                            productoEliminarView.actualizarTextos();

                            productoListaView.setMensajeInternacionalizacion(mensajeHandler);
                            productoListaView.actualizarTextos();

                            recuperarCuentaView.setMensajeInternacionalizacion(mensajeHandler);
                            recuperarCuentaView.actualizarTextos();
                        });

                    }
                }
            });
        });
    }
}
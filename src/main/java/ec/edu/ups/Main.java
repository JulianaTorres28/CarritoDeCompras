package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {

            UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
            LoginView loginView = new LoginView();
            UsuarioRegistroView usuarioRegistroView = new UsuarioRegistroView();
            UsuarioAdminView usuarioAdminView = new UsuarioAdminView();

            UsuarioController usuarioController = new UsuarioController(
                    usuarioDAO,
                    loginView,
                    usuarioRegistroView,
                    usuarioAdminView
            );

            loginView.setVisible(true);

            loginView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                    if (usuarioAutenticado != null) {

                        MensajeInternacionalizacionHandler mensajeHandler = new MensajeInternacionalizacionHandler("es", "EC");

                        ProductoDAO productoDAO = new ProductoDAOMemoria();
                        CarritoDAO carritoDAO = new CarritoDAOMemoria();

                        MenuPrincipalView principalView = new MenuPrincipalView(mensajeHandler);
                        ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                        ProductoListaView productoListaView = new ProductoListaView();
                        ProductoActualizarView productoActualizarView = new ProductoActualizarView();
                        ProductoEliminarView productoEliminarView = new ProductoEliminarView();
                        CarritoAnadirView carritoAnadirView = new CarritoAnadirView(mensajeHandler);
                        CarritoListaView carritoListaView = new CarritoListaView();

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

                        principalView.mostrarMensaje("Bienvenido: " + usuarioAutenticado.getUsername());
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
                            principalView.dispose();
                            LoginView newLoginView = new LoginView();
                            UsuarioRegistroView newRegistroView = new UsuarioRegistroView();
                            UsuarioAdminView newAdminView = new UsuarioAdminView();

                            new UsuarioController(usuarioDAO, newLoginView, newRegistroView, newAdminView);

                            newLoginView.setVisible(true);
                        });

                        principalView.getMenuItemSalir().addActionListener(ev -> {
                            System.exit(0);
                        });

                        principalView.getMenuItemIdiomaEspanol().addActionListener(ev ->
                                principalView.cambiarIdioma("es", "EC"));

                        principalView.getMenuItemIdiomaIngles().addActionListener(ev ->
                                principalView.cambiarIdioma("en", "US"));

                        principalView.getMenuItemIdiomaFrances().addActionListener(ev ->
                                principalView.cambiarIdioma("fr", "FR"));
                    }
                }
            });
        });
    }
}

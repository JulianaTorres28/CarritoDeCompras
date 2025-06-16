package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.modelo.Producto;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                ProductoAnadirView productoView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                GestorDeProductoView gestorDeProductoView = new GestorDeProductoView();
                ProductoDAO productoDAO = new ProductoDAOMemoria();

                new ProductoController(productoDAO, productoView, productoListaView, gestorDeProductoView);
            }
        });
    }
}

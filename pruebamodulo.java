package com.gescomin;

import com.gescomin.dao.ProductoDao;
import com.gescomin.modelo.Producto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class PruebaModuloProducto {

    public static void main(String[] args) {
        ProductoDao productoDao = new ProductoDao();

        try {
            // 1. Insertar
            Producto nuevoProducto = new Producto(
                    "SKU-001",
                    "Cinta raso tricolor 2 cm",
                    new BigDecimal("4500.00"),
                    25,
                    true
            );
            boolean insertado = productoDao.insertarProducto(nuevoProducto);
            System.out.println("Insertado: " + insertado + " -> " + nuevoProducto);

            // 2. Consultar todos
            List<Producto> productos = productoDao.listarProductos();
            System.out.println("Total productos registrados: " + productos.size());

            // 3. Actualizar
            nuevoProducto.setStockActual(40);
            nuevoProducto.setPrecioUnitario(new BigDecimal("4800.00"));
            boolean actualizado = productoDao.actualizarProducto(nuevoProducto);
            System.out.println("Actualizado: " + actualizado);

            // 4. Eliminar
            boolean eliminado = productoDao.eliminarProducto(nuevoProducto.getIdProducto());
            System.out.println("Eliminado: " + eliminado);

        } catch (SQLException e) {
            System.err.println("Error en operación JDBC: " + e.getMessage());
        }
    }
}

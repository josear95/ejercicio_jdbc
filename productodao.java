package com.gescomin.dao;

import com.gescomin.config.ConexionBD;
import com.gescomin.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDao {

    private static final String SQL_INSERTAR =
            "INSERT INTO productos (codigo_sku, nombre_producto, precio_unitario, stock_actual, activo) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_LISTAR_TODOS =
            "SELECT id_producto, codigo_sku, nombre_producto, precio_unitario, stock_actual, activo FROM productos ORDER BY id_producto ASC";
    private static final String SQL_BUSCAR_POR_ID =
            "SELECT id_producto, codigo_sku, nombre_producto, precio_unitario, stock_actual, activo FROM productos WHERE id_producto = ?";
    private static final String SQL_ACTUALIZAR =
            "UPDATE productos SET codigo_sku = ?, nombre_producto = ?, precio_unitario = ?, stock_actual = ?, activo = ? WHERE id_producto = ?";
    private static final String SQL_ELIMINAR =
            "DELETE FROM productos WHERE id_producto = ?";

    public boolean insertarProducto(Producto producto) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_INSERTAR, Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, producto.getCodigoSku());
            sentencia.setString(2, producto.getNombreProducto());
            sentencia.setBigDecimal(3, producto.getPrecioUnitario());
            sentencia.setInt(4, producto.getStockActual());
            sentencia.setBoolean(5, producto.isActivo());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet llavesGeneradas = sentencia.getGeneratedKeys()) {
                    if (llavesGeneradas.next()) {
                        producto.setIdProducto(llavesGeneradas.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }

    public List<Producto> listarProductos() throws SQLException {
        List<Producto> listaProductos = new ArrayList<>();

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_LISTAR_TODOS);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                listaProductos.add(mapearProducto(resultado));
            }
        }
        return listaProductos;
    }

    public Optional<Producto> buscarProductoPorId(int idProducto) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_BUSCAR_POR_ID)) {

            sentencia.setInt(1, idProducto);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return Optional.of(mapearProducto(resultado));
                }
            }
        }
        return Optional.empty();
    }

    public boolean actualizarProducto(Producto producto) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_ACTUALIZAR)) {

            sentencia.setString(1, producto.getCodigoSku());
            sentencia.setString(2, producto.getNombreProducto());
            sentencia.setBigDecimal(3, producto.getPrecioUnitario());
            sentencia.setInt(4, producto.getStockActual());
            sentencia.setBoolean(5, producto.isActivo());
            sentencia.setInt(6, producto.getIdProducto());

            return sentencia.executeUpdate() > 0;
        }
    }

    public boolean eliminarProducto(int idProducto) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_ELIMINAR)) {

            sentencia.setInt(1, idProducto);
            return sentencia.executeUpdate() > 0;
        }
    }

    private Producto mapearProducto(ResultSet resultado) throws SQLException {
        Producto producto = new Producto();
        producto.setIdProducto(resultado.getInt("id_producto"));
        producto.setCodigoSku(resultado.getString("codigo_sku"));
        producto.setNombreProducto(resultado.getString("nombre_producto"));
        producto.setPrecioUnitario(resultado.getBigDecimal("precio_unitario"));
        producto.setStockActual(resultado.getInt("stock_actual"));
        producto.setActivo(resultado.getBoolean("activo"));
        return producto;
    }
}

package com.gescomin.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL_CONEXION =
            "jdbc:mysql://localhost:3306/gescomin_db?useSSL=false&serverTimezone=America/Bogota";
    private static final String USUARIO_BD = "root";
    private static final String CONTRASENA_BD = "";

    private ConexionBD() {
        // Evita instanciación directa de la clase utilitaria
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL_CONEXION, USUARIO_BD, CONTRASENA_BD);
    }
}

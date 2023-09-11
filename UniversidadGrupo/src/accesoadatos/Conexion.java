package accesoadatos;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String URL = "jdbc:mariadb://localhost/";
    private static final String DB = "universidadgrupo35";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    private Conexion() {
    }

    public static Connection getConexion() {
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL+DB,USUARIO,PASSWORD);

                JOptionPane.showMessageDialog(null, "conectado");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "error al cargar los Drivers " + ex.getMessage());
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "error al conectarse a la BD " + ex.getMessage());
            }
        }
        return connection;
    }

}

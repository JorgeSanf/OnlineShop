//J
package onlineshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 2.1.1.- A function to create a connection with database with chosen user, password, IP and port
 *
 */
public class Conexion {

    private final String url = "jdbc:mysql://localhost:3306/onlineshop";
    private Connection conexion;

    public Connection Conectar() throws SQLException { 

        String usuario = Sesion.user;
        String pass = Sesion.contraseñaConexion();
        
        try {
            conexion = DriverManager.getConnection(url, usuario, pass);
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
            System.out.println(ex.getMessage());
            System.out.println("");
            //conexion.close();
        }

        return conexion;

    }
       
}

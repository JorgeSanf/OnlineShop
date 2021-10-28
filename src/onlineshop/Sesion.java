//J
package onlineshop;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Jorge
 */
public class Sesion {
    
    /* Clase Sesion para guardar las variables del usuario, su contraseña
    y el tipo de usuario, y para comprobar que el usuario existe y que la
    contraseña es correcta.
    */

    protected static String user;
    private static String pass;
    protected static int tipo;
    protected static boolean valido = false;
    Conexion conexion = new Conexion();

    public Sesion(String user, String pass) {

        Sesion.user = user;
        Sesion.pass = pass;
    }

    public static Sesion dimeUsuarioContraseña() {

        Scanner entrada = new Scanner(System.in);

        System.out.println("");
        System.out.println("--- INICIO DE SESIÓN ---");
        System.out.println("");
        System.out.print("Usuario: ");
        String usuario = entrada.nextLine();
        System.out.println("");
        System.out.print("Contraseña: ");
        String pase = entrada.nextLine();
        System.out.println("");

        Sesion sesion = new Sesion(usuario, pase);
        return sesion;
    }

    protected static String contraseñaConexion() {
        // Necesito esta función para hacer la contraseña privada
        return pass;
    }

    protected void comprobarSesion() {

        boolean userExiste = userExiste();
        boolean passCorrecto = false;

        if (userExiste) {

            passCorrecto = passCorrecto();

            if (passCorrecto) {

                valido = true;
                Sesion.mensajeInicio();

            } else {
                System.out.println("Contraseña incorrecta");
            }
        } else {
            System.out.println("Usuario inexistente");
        }
    }

    private boolean userExiste() {

        boolean userValido = false;
        int userV = 0;

        String sqlCliente = "SELECT EXISTS(SELECT 1 FROM clients WHERE usuario = ?)";
        // 2.1.2

        try {
            PreparedStatement stmt = conexion.Conectar().prepareStatement(sqlCliente);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                userV = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("1" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }

        if (userV == 1) {

            userValido = true;
            tipo = 1;
        }

        userV = 0;

        if (!userValido) {

            String sqlProveedor = "SELECT EXISTS(SELECT 1 FROM suppliers WHERE usuario = ?)";
            // 2.1.2

            try {

                PreparedStatement stmt = conexion.Conectar().prepareStatement(sqlProveedor);
                stmt.setString(1, user);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    userV = rs.getInt(1);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
            }

            if (userV == 1) {

                userValido = true;
                tipo = 2;
            }

        }

        return userValido;
    }

    private boolean passCorrecto() {

        boolean passValido = false;
        String passBD = "";

        String sqlCliente = "SELECT password FROM usuarios WHERE usuario=?";

        try {
            PreparedStatement stmt = conexion.Conectar().prepareStatement(sqlCliente);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                passBD = rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }

        if (passBD.contentEquals(pass)) {

            passValido = true;
        }

        return passValido;
    }

    protected static void mensajeInicio() {
        // 2.1.2- A function that returns if the user it’s a client or supplier.

        String inicio = "Ha iniciado sesión como ";

        switch (tipo) {

            case 1:
                inicio += "cliente.";
                break;

            case 2:
                inicio += "proveedor.";
                break;
        }

        System.out.println(inicio);
        System.out.println("");
    }

}

//J
package onlineshop;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class Acciones {

    Scanner inString = new Scanner(System.in);
    Scanner inInt = new Scanner(System.in);
    Conexion conexion = new Conexion();
    final String sqlu1 = Sesion.user + "')";
    final String sqlu2 = Sesion.user + "'";

    // 2.2.1 - Suppliers can see their product list.
    public void provLista() {

        String sql0 = "SELECT * FROM products WHERE supplier IN "
                + "(SELECT nif FROM suppliers WHERE usuario='";
        String sql = sql0.concat(sqlu1);

        try {
            Statement stmt = conexion.Conectar().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("cod_prod")
                        + " " + rs.getString("nombre")
                        + " " + rs.getString("price")
                        + " " + rs.getString("supplier")
                        + " " + rs.getString("stock"));
            }

            conexion.Conectar().close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 2.2.2 - Suppliers can update their products.
    public void provUpdate() {

        int stock;
        float precio;
        String cod, nom;

        String sql0 = "UPDATE products SET price=?, stock=?, nombre=?"
                + "WHERE cod_prod=? AND supplier IN "
                + "(SELECT nif FROM suppliers WHERE usuario='";
        String sql = sql0.concat(sqlu1);

        System.out.println("Código: ");
        cod = inString.nextLine();

        System.out.println("Nombre: ");
        nom = inString.nextLine();

        System.out.println("Precio: ");
        precio = inInt.nextFloat();

        System.out.println("Stock: ");
        stock = inInt.nextInt();

        try {
            PreparedStatement stmt = conexion.Conectar().prepareStatement(sql);
            stmt.setFloat(1, precio);
            stmt.setInt(2, stock);
            stmt.setString(3, nom);
            stmt.setString(4, cod);
            stmt.executeUpdate();

            conexion.Conectar().close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    // 2.2.2 - Suppliers can delete their products.
    public void provDelete() {

        String sql = "DELETE FROM products WHERE cod_prod=?";

        System.out.println("Código del producto a eliminar: ");
        String cod = inString.nextLine();

        try {
            PreparedStatement stmt = conexion.Conectar().prepareStatement(sql);
            stmt.setString(1, cod);
            stmt.executeUpdate();

            conexion.Conectar().close();

        } catch (SQLException e) {
            System.out.println("1" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }

    }

    // 2.2.3 - Suppliers can insert new products.
    public void provInsert() {

        String sql = "INSERT INTO products VALUES(?,?,?,?,?)";

        System.out.println("Código del proveedor: (CARGAL: 'Q7182500D')");
        String user = inString.nextLine();

        System.out.println("Código del producto a añadir: ");
        String cod = inString.nextLine();

        System.out.println("Nombre");
        String nom = inString.nextLine();

        System.out.println("Precio");
        float precio = inInt.nextFloat();

        System.out.println("Stock");
        int stock = inInt.nextInt();

        try {
            PreparedStatement stmt = conexion.Conectar().prepareStatement(sql);
            stmt.setString(1, cod);
            stmt.setString(2, nom);
            stmt.setFloat(3, precio);
            stmt.setInt(5, stock);
            stmt.setString(4, user);
            stmt.executeUpdate();

            conexion.Conectar().close();

        } catch (SQLException e) {
            System.out.println("2" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }
    }

    /* 2.3.1 - Clients can see their data in “usuarios” and “clients” tables,
        all data togheter. */
    public void mostrarDatos() {

        String sql0 = "SELECT * FROM clients NATURAL JOIN usuarios WHERE usuario='";
        String sql = sql0.concat(sqlu2);

        try {
            Statement stmt = conexion.Conectar().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("usuario") + " "
                        + rs.getString("dni") + " "
                        + rs.getString("city") + " "
                        + rs.getInt("balance") + " "
                        + rs.getInt("discount") + " "
                        + rs.getString("nombre") + " "
                        + rs.getString("apellido")// + " "
                /*+ rs.getString("password")*/); // Excluido por seguridad
            }

            conexion.Conectar().close();

        } catch (SQLException e) {
            System.out.println("3" + e.getMessage());
        }

    }

    /* 2.3.2 - Clients can update their data (except “usuario”, “discount”
        and “account”) in “usuarios” and “clients” tables, together.*/
    public void cambiarDatos() {

        String sql1 = "UPDATE clients SET dni=?, city=? WHERE usuario=?";
        String sql2 = "UPDATE usuarios SET nombre=?, apellido=?, password=? "
                + "WHERE usuario=?";
        String dni, city, nombre, apellido, password;

        System.out.println("Nuevos datos");

        System.out.println("DNI: ");
        dni = inString.nextLine();

        System.out.println("Ciudad: ");
        city = inString.nextLine();

        System.out.println("Nombre: ");
        nombre = inString.nextLine();

        System.out.println("Apellido: ");
        apellido = inString.nextLine();

        System.out.println("Contraseña: ");
        password = inString.nextLine();

        try {
            PreparedStatement stmt1 = conexion.Conectar().prepareStatement(sql1);
            PreparedStatement stmt2 = conexion.Conectar().prepareStatement(sql2);

            stmt1.setString(1, dni);
            stmt1.setString(2, city);
            stmt1.setString(3, Sesion.user);
            stmt2.setString(1, nombre);
            stmt2.setString(2, apellido);
            stmt2.setString(3, password);
            stmt2.setString(4, Sesion.user);

            stmt1.executeUpdate();
            stmt2.executeUpdate();

            System.out.println("Datos actualizados.");

            conexion.Conectar().close();

        } catch (SQLException e) {
            System.out.println("4" + e.getMessage());
        }
    }

    // 2.3.3 - Clients can add some quantity to their account.
    public void añadirDinero() {

        String sql0 = "UPDATE clients SET balance = clients.balance + ? WHERE usuario = '";
        String sql = sql0.concat(sqlu2);

        System.out.println("Cantidad a añadir: ");
        int cantidad = inInt.nextInt();

        try {
            PreparedStatement stmt = conexion.Conectar().prepareStatement(sql);
            stmt.setInt(1, cantidad);
            stmt.executeUpdate();

            conexion.Conectar().close();

        } catch (SQLException e) {
            System.out.println("5" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }
    }

    /* 2.3.4 - Clients can see their purchases (shopping) between 2 chosen dates.
        It will show just num, date and total spent for each purchase. */
    public void verCompras() {

        String sql0 = "SELECT sh_num, fecha, quantity*price AS gasto FROM "
                + "shopping NATURAL JOIN shopping_lines NATURAL JOIN products "
                + "WHERE (fecha BETWEEN ? AND ?) AND client = '";
        String sql = sql0.concat(sqlu2);

        System.out.println("Formato: YYYY-MM-DD");
        System.out.println("Fecha inicial: ");
        String fecha1 = inString.nextLine();
        System.out.println("Fecha final: ");
        String fecha2 = inString.nextLine();

        try {
            PreparedStatement stmt = conexion.Conectar().prepareStatement(sql);
            stmt.setString(1, fecha1);
            stmt.setString(2, fecha2);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("sh_num") + " "
                        + rs.getString("fecha") + " "
                        + rs.getFloat("gasto"));
            }

            conexion.Conectar().close();

        } catch (SQLException e) {
            System.out.println("6" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }
    }

    /* 2.3.5 - Clients can select a purchase and see the bill (line_num,
        name of product, quantity, price, quantity*price and the total spent in the bill. */
    public void verFactura() {

        String sql0 = "SELECT sh_num, line_num, nombre, quantity, price, "
                + "quantity*price AS gasto FROM shopping NATURAL JOIN shopping_lines "
                + "NATURAL JOIN products WHERE sh_num = ? AND client = '";
        String sql = sql0.concat(sqlu2);

        System.out.println("Solo puedes ver tus facturas.");
        System.out.println("Número de compra: ");
        String numero = inString.nextLine();

        try {
            PreparedStatement stmt = conexion.Conectar().prepareStatement(sql);
            stmt.setString(1, numero);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("sh_num") + " "
                        + rs.getString("line_num") + " "
                        + rs.getString("nombre") + " "
                        + rs.getInt("quantity") + " "
                        + rs.getFloat("price") + " "
                        + rs.getFloat("gasto"));
            }

            conexion.Conectar().close();

        } catch (SQLException e) {
            System.out.println("7" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }
    }

    /* A partir de aquí, todos los métodos están relacionados con el apartado
    "Comprar" de los puntos 2.3.6 y 2.3.7
     */
    public int hayStock(String codpro) {

        // Método para comprobar el stock
        String sql0 = "SELECT stock FROM products WHERE cod_prod = '";
        String sql = sql0.concat(codpro + "'");
        int stock = 0;

        if (codpro.equalsIgnoreCase("0")) {

            System.out.println("Saliendo...");
        } else {
            try {
                Statement stmt = conexion.Conectar().createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                stock = rs.getInt(1);

                conexion.Conectar().close();

            } catch (SQLException e) {
                System.out.println("8" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
            }
        }

        return stock;
    }

    public boolean existeProd(String codpro) {

        // Método para comprobar que el producto existe
        boolean exProd = false;
        int prodV = 0;

        String sql = "SELECT EXISTS(SELECT 1 FROM products WHERE cod_prod = ?)";

        if (!codpro.equalsIgnoreCase("0")) {
            try {
                PreparedStatement stmt = conexion.Conectar().prepareStatement(sql);
                stmt.setString(1, codpro);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    prodV = rs.getInt(1);
                }

                conexion.Conectar().close();

            } catch (SQLException e) {
                System.out.println("9" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
            }

            if (prodV == 1) {

                exProd = true;
            }
        }

        return exProd;
    }

    public double efectuarCompra(int cantidad, String codpro, int numshop, int linenum) {

        // Método para realizar la transacción en la BBDD

        Acciones accion = new Acciones();
        double precioParcial = accion.calculoPrecio(cantidad, codpro);

        try {

            // Creo una nueva conexión para poder desactivar el autocommit desde aquí
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshop",
                    Sesion.user, Sesion.contraseñaConexion());
            con.setAutoCommit(false);

            String sql1 = "UPDATE products SET stock = stock - ? WHERE cod_prod = ?";

            PreparedStatement stmt0 = con.prepareStatement(sql1);
            stmt0.setInt(1, cantidad);
            stmt0.setString(2, codpro);
            stmt0.executeUpdate();

            String sql2 = "INSERT INTO shopping_lines VALUES (?,?,?,?)";

            PreparedStatement stmt1 = con.prepareStatement(sql2);
            stmt1.setInt(1, numshop);
            stmt1.setInt(2, linenum);
            stmt1.setString(3, codpro);
            stmt1.setInt(4, cantidad);
            stmt1.executeUpdate();

            // Registrar linea
            if (accion.verificadorStock(codpro, cantidad)) { // 
                System.out.println("...");
                con.commit();
            } else {
                conexion.Conectar().rollback();
                System.out.println("Se ha agotado el stock.");
                precioParcial = 0;
            }
            con.setAutoCommit(true);
            con.close();

        } catch (SQLException e) {
            System.out.println("10" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }

        return precioParcial;
    }

    public double calculoPrecio(int cantidad, String codpro) {

        double precioParcial = -1000;
        double precio = 0;

        try {

            String sql1 = "SELECT price FROM products WHERE cod_prod = ";
            String sql2 = sql1.concat("'" + codpro + "'");
            Statement stmt1 = conexion.Conectar().createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql2);
            rs1.next();
            precio = rs1.getInt(1);

        } catch (SQLException e) {
            System.out.println("11" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }
        precioParcial = precio * cantidad;

        return precioParcial;
    }

    private boolean verificadorStock(String codpro, int cantidad) {

        boolean okstock = false;

        try {
            String sql3 = "SELECT stock FROM products WHERE cod_prod = ";
            String sql4 = sql3.concat("'" + codpro + "'");
            Statement stmt2 = conexion.Conectar().createStatement();
            ResultSet rs2 = stmt2.executeQuery(sql4);
            rs2.next();
            int stock = rs2.getInt(1);

            conexion.Conectar().close();

            okstock = stock > cantidad;

        } catch (SQLException e) {
            System.out.println("12" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }

        return okstock;
    }

    public boolean suficienteSaldoStock(String codpro, int cantidad, double precio) {

        /* Método que verifica si se cumplen las condiciones:
        1. Suficiente saldo
        2. Suficiente stock
         */
        Acciones accion = new Acciones();

        boolean suficiente = false;

        try {
            double dinero = accion.dimeSaldo();

            String sql3 = "SELECT stock FROM products WHERE cod_prod = ";
            String sql4 = sql3.concat("'" + codpro + "'");
            Statement stmt2 = conexion.Conectar().createStatement();
            ResultSet rs2 = stmt2.executeQuery(sql4);
            rs2.next();
            int stock = rs2.getInt(1);

            conexion.Conectar().close();

            boolean bsaldo = dinero > precio * cantidad;
            boolean bstock = stock > cantidad;

            if (bsaldo && bstock) {
                suficiente = true;
            }

        } catch (SQLException e) {
            System.out.println("13" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }

        return suficiente;
    }

    public int numShop() {

        int numshop = 100;

        try {
            String sql = "SELECT sh_num FROM shopping ORDER BY sh_num DESC LIMIT 1";
            Statement stmt = conexion.Conectar().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            numshop = rs.getInt(1) + 1;
        } catch (SQLException e) {
            System.out.println("14" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }

        return numshop;
    }

    public void registrarCompra(int shnum) {

        String sql2 = "INSERT INTO shopping VALUES (?,?,?)";

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        try {
            PreparedStatement stmt1 = conexion.Conectar().prepareStatement(sql2);
            stmt1.setInt(1, shnum);
            stmt1.setDate(2, sqlDate);
            stmt1.setString(3, Sesion.user);

            stmt1.executeUpdate();

        } catch (SQLException e) {
            System.out.println("15" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }

    }

    public void accionCompras() {

        // Menú y llamada al carro para añadir línea / efectuar compra
        Carro carrito = new Carro();
        Scanner entrada = new Scanner(System.in);
        int op = 0;
        carrito.añadirCarro();

        try {
            do {

                switch (op) {
                    case 1 ->
                        carrito.añadirCarro();

                    case 2 ->
                        carrito.efectuarCompra();
                }

                if (op < 0 || op > 2) {
                    System.out.println("Número no válido");
                }

                Menu.menuCompras();
                op = entrada.nextInt();

            } while (op != 0);

        } catch (SQLException e) {
            System.out.println("16" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }

    }

    public double dimeSaldo() {

        double saldo = -1000;

        try {
            String sql0 = "SELECT balance FROM clients WHERE usuario = '";
            String sql = sql0.concat(sqlu2);
            Statement stmt = conexion.Conectar().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            saldo = rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("17" + e.getMessage() + " \t " + e.getSQLState() + " \t " + e.getErrorCode());
        }

        return saldo;
    }

    public void actualizarBalance(double total) {

        try {

            String sql0 = "UPDATE clients SET balance = balance - ? WHERE usuario = '";
            String sql1 = sql0.concat(sqlu2);

            PreparedStatement stmt0 = conexion.Conectar().prepareStatement(sql1);
            stmt0.setDouble(1, total);
            stmt0.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("18" + Acciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

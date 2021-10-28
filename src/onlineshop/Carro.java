//J
package onlineshop;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;


/* Para las compras, creo una clase compra que recibe el código del producto
y la cantidad a comprar. Se comprueba que existe el producto y que hay stock,
y se guarda la compra en una colección.

Cuando el usuario acaba de comprar, pasa a efectuar la compra. Se calcula el
precio total, se comprueba que tenga dinero para realizar la compra y que haya
stock. Una vez comprobado, se recorre la colección y se van enviando los datos
a la BBDD. */

class Compra {

    protected String codpro;
    protected int cantidad;

    public Compra(String codpro, int cantidad) {

        this.codpro = codpro;
        this.cantidad = cantidad;
    }

}

public class Carro implements Serializable {

    LinkedList<Compra> compras;

    public Carro() {

        this.compras = new LinkedList<>();
    }

    public void añadirCarro() {

        Scanner entrada = new Scanner(System.in);
        Compra linea;
        String codpro;
        int cantidad = 0;

        do {
            System.out.println("Código del producto: (0 = Salir)");
            codpro = entrada.nextLine();

            if ((!Carro.existeProd(codpro)) & !codpro.equalsIgnoreCase("0")) {
                System.out.println("No existe el producto.");
            }
        } while (!Carro.existeProd(codpro) && !codpro.equals("0"));

        int stock = Carro.hayStock(codpro);

        if (Carro.existeProd(codpro)) {
            do {
                System.out.println("Stock: " + stock);
                System.out.println("Cantidad: (0 = Salir)");
                cantidad = entrada.nextInt();

                if (cantidad > stock) {
                    System.out.println("No hay suficiente stock.");
                }

            } while (cantidad != 0 && cantidad > stock);
        }

        if (cantidad != 0) {
            linea = new Compra(codpro, cantidad);
            compras.add(linea);
        }
    }

    public void efectuarCompra() throws SQLException {

        int cantidad;
        String codpro;
        Acciones accion = new Acciones();
        double pasta = 0;

        boolean correcto = true;

        int numshop = accion.numShop();
        double total = 0;
        //int linenum = 1;

        for (Compra compra : compras) {
            cantidad = compra.cantidad;
            codpro = compra.codpro;
            pasta += accion.calculoPrecio(cantidad, codpro);

            correcto = correcto & accion.suficienteSaldoStock(codpro, cantidad, total);
        }

        if (correcto && (accion.dimeSaldo()>pasta)) { 
            
            /* Condiciones para registrar la compra:
            1. Que haya suficiente saldo y stock para cada linea
            2. Que haya suficiente saldo para el importe total
            
            Que haya saldo para cada compra es redundante si hay saldo para el total,
            pero necesito verificarlo cada vez para evitar que el usuario añada
            una linea al carro sin suficiente
            */

            accion.registrarCompra(numshop);

            for (Compra compra : compras) {

                cantidad = compra.cantidad;
                codpro = compra.codpro;
                System.out.println(cantidad + codpro);

                total += accion.efectuarCompra(cantidad, codpro, numshop, 
                        compras.indexOf(compra)+1);
            }
            
            accion.actualizarBalance(total);

            System.out.println("La operación ha sido de " + total + " euros");
            System.out.println("");
        }

    }

    public static int hayStock(String codpro) {

        int stock;
        Acciones accion = new Acciones();
        stock = accion.hayStock(codpro);

        return stock;
    }

    public static boolean existeProd(String codpro) {

        boolean exProd = false;
        Acciones accion = new Acciones();
        exProd = accion.existeProd(codpro);

        return exProd;
    }
}

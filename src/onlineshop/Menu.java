package onlineshop;

/**
 *
 * @author 7NDAM
 */
public class Menu {

    Acciones accion = new Acciones();

    public static void menuCliente() {

        String[] mcliente = {"0. Salir", "1. Mostrar datos", "2. Cambiar datos",
            "3. Compras por fecha", "4. Ver factura", "5. Añadir dinero",
            "6. Comprar"
        };

        for (String opc : mcliente) {
            System.out.println(opc);
        }

    }

    public static void menuProveedor() {

        String[] mproveedor = {"0. Salir",
            "1. Lista de productos", "2. Actualizar producto",
            "3. Borrar producto"
        };

        for (String opc : mproveedor) {
            System.out.println(opc);
        }

    }

    protected void accionCliente(int op) {

        switch (op) {
            case 1:
                accion.mostrarDatos();
                break;
            case 2:
                System.out.println("Datos viejos: ");
                accion.mostrarDatos();
                accion.cambiarDatos();
                break;
            case 3:
                accion.verCompras();
                break;
            case 4:
                accion.verFactura();
                break;
            case 5:
                accion.añadirDinero();
                break;
            case 6:
                accion.accionCompras();
                break;
        }

    }

    protected void accionProveedor(int op) {

        switch (op) {

            case 1:
                accion.provLista();
                break;

            case 2:
                accion.provUpdate();
                break;

            case 3:
                accion.provDelete();
                break;

            case 4:
                accion.provInsert();
                break;

        }

    }

    protected static void menuCompras() {

        String[] mproveedor = {"0. Salir",
            "1. Añadir producto", "2. Efectuar compra",};

        for (String opc : mproveedor) {
            System.out.println(opc);
        }

    }


}

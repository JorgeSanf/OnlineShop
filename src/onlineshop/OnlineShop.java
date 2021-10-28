
package onlineshop;

import java.util.Scanner;


public class OnlineShop {

    public static void main(String[] args) {

        OnlineShop.usuariosRegistrados();
        
        do {
            Sesion sesion = Sesion.dimeUsuarioContraseña();
            try {
                sesion.comprobarSesion();
            } catch (Exception e) {
                System.out.println("Usuario no registrado.");
                //System.exit(1);
            }
        } while (Sesion.valido == false);

        Menu menu = new Menu();
        
        Scanner entrada = new Scanner(System.in);
        int op;

        do {

            switch (Sesion.tipo) {

                case 1:
                    Menu.menuCliente();
                    break;

                case 2:
                    Menu.menuProveedor();
                    break;
            }

            op = entrada.nextInt();
            System.out.println("");

            switch (Sesion.tipo) {

                case 1:
                    menu.accionCliente(op);
                    break;

                case 2:
                    menu.accionProveedor(op);
                    break;
            }

            System.out.println("");

        } while (op != 0);
    }

    private static void usuariosRegistrados() {

        System.out.println("Los usuarios con cuenta en la base de datos son:");
        System.out.println("");
        System.out.println("Cliente:");
        System.out.println("Nombre: JUAPAS; Contraseña: jK1kL3fI");
        System.out.println("");
        System.out.println("Proveedor:");
        System.out.println("Nombre: CARGAL; Contraseña: fqifbdof");
        System.out.println("");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jorge
 */
public class AccionesTest {
    
    public AccionesTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of provLista method, of class Acciones.
     */
    @Test
    public void testProvLista() {
        System.out.println("provLista");
        Acciones instance = new Acciones();
        instance.provLista();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of provUpdate method, of class Acciones.
     */
    @Test
    public void testProvUpdate() {
        System.out.println("provUpdate");
        Acciones instance = new Acciones();
        instance.provUpdate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of provDelete method, of class Acciones.
     */
    @Test
    public void testProvDelete() {
        System.out.println("provDelete");
        Acciones instance = new Acciones();
        instance.provDelete();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of provInsert method, of class Acciones.
     */
    @Test
    public void testProvInsert() {
        System.out.println("provInsert");
        Acciones instance = new Acciones();
        instance.provInsert();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarDatos method, of class Acciones.
     */
    @Test
    public void testMostrarDatos() {
        System.out.println("mostrarDatos");
        Acciones instance = new Acciones();
        instance.mostrarDatos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cambiarDatos method, of class Acciones.
     */
    @Test
    public void testCambiarDatos() {
        System.out.println("cambiarDatos");
        Acciones instance = new Acciones();
        instance.cambiarDatos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirDinero method, of class Acciones.
     */
    @Test
    public void testAñadirDinero() {
        System.out.println("a\u00f1adirDinero");
        Acciones instance = new Acciones();
        instance.añadirDinero();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verCompras method, of class Acciones.
     */
    @Test
    public void testVerCompras() {
        System.out.println("verCompras");
        Acciones instance = new Acciones();
        instance.verCompras();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verFactura method, of class Acciones.
     */
    @Test
    public void testVerFactura() {
        System.out.println("verFactura");
        Acciones instance = new Acciones();
        instance.verFactura();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hayStock method, of class Acciones.
     */
    @Test
    public void testHayStock() {
        System.out.println("hayStock");
        String codpro = "";
        Acciones instance = new Acciones();
        int expResult = 0;
        int result = instance.hayStock(codpro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existeProd method, of class Acciones.
     */
    @Test
    public void testExisteProd() {
        System.out.println("existeProd");
        String codpro = "";
        Acciones instance = new Acciones();
        boolean expResult = false;
        boolean result = instance.existeProd(codpro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of efectuarCompra method, of class Acciones.
     */
    @Test
    public void testEfectuarCompra() {
        System.out.println("efectuarCompra");
        int cantidad = 0;
        String codpro = "";
        int numshop = 0;
        int linenum = 0;
        Acciones instance = new Acciones();
        double expResult = 0.0;
        double result = instance.efectuarCompra(cantidad, codpro, numshop, linenum);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculoPrecio method, of class Acciones.
     */
    @Test
    public void testCalculoPrecio() {
        System.out.println("calculoPrecio");
        int cantidad = 0;
        String codpro = "";
        Acciones instance = new Acciones();
        double expResult = 0.0;
        double result = instance.calculoPrecio(cantidad, codpro);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of suficienteSaldoStock method, of class Acciones.
     */
    @Test
    public void testSuficienteSaldoStock() {
        System.out.println("suficienteSaldoStock");
        String codpro = "";
        int cantidad = 0;
        double precio = 0.0;
        Acciones instance = new Acciones();
        boolean expResult = false;
        boolean result = instance.suficienteSaldoStock(codpro, cantidad, precio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numShop method, of class Acciones.
     */
    @Test
    public void testNumShop() {
        System.out.println("numShop");
        Acciones instance = new Acciones();
        int expResult = 0;
        int result = instance.numShop();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarCompra method, of class Acciones.
     */
    @Test
    public void testRegistrarCompra() {
        System.out.println("registrarCompra");
        int shnum = 0;
        Acciones instance = new Acciones();
        instance.registrarCompra(shnum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accionCompras method, of class Acciones.
     */
    @Test
    public void testAccionCompras() {
        System.out.println("accionCompras");
        Acciones instance = new Acciones();
        instance.accionCompras();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dimeSaldo method, of class Acciones.
     */
    @Test
    public void testDimeSaldo() {
        System.out.println("dimeSaldo");
        Acciones instance = new Acciones();
        double expResult = 0.0;
        double result = instance.dimeSaldo();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarBalance method, of class Acciones.
     */
    @Test
    public void testActualizarBalance() {
        System.out.println("actualizarBalance");
        double total = 0.0;
        Acciones instance = new Acciones();
        instance.actualizarBalance(total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

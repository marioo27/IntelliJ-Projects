package com.dam.di;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class OperacionesTest {

    @BeforeAll
    public static void antesDeTodo(){
        System.out.println("@BeforeAll");
    }

    @AfterAll
    public static void despuesDeTodo(){
        System.out.println("@AfterAll");
    }


    @BeforeEach
    public void iniciar(){
        System.out.println("@BeforeEach");
    }

    @AfterEach
    public void terminar(){
        System.out.println("@AfterEach");
    }

    @Test
    public void testSuma() {
        Operaciones o = new Operaciones(3, 2);
        assertEquals(5, o.suma(o.getA(), o.getB()), "El resultado de la suma (3+2) es 5");
    }

    @Test
    public void testResta() {
        Operaciones o = new Operaciones(5, 3);
        assertEquals(2, o.resta(o.getA(), o.getB()), "El resultado de la resta (5-3) es 2");
    }

    @Test
    public void testMultiplicacion() {
        Operaciones o = new Operaciones(4, 3);
        assertEquals(12, o.multiplicacion(o.getA(), o.getB()), "El resultado de la multiplicación (4*3) es 12");
    }

    @Test
    public void testDivision() {
        Operaciones o = new Operaciones(10, 2);
        assertEquals(5, o.division(o.getA(), o.getB()), "El resultado de la división (10/2) es 5");

        Operaciones oDivCero = new Operaciones(10, 0);
        assertThrows(ArithmeticException.class, () -> oDivCero.division(oDivCero.getA(), oDivCero.getB()), "La división por cero debe lanzar una ArithmeticException");
    }
}
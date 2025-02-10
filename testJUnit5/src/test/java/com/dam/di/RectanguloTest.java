package com.dam.di;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RectanguloTest {

    // Me ejecuto una sola vez al inicio del Test. Importante: Tiene que ser "static"
    @BeforeAll
    public static void antesDeTodo(){
        System.out.println("@BeforeAll");
    }
    //   // Me ejecuto una sola vez al final del Test
    @AfterAll
    public static void despuesDeTodo(){
        System.out.println("@AfterAll");
    }


    // Me ejecuto antes de cada metodo de prueba
    @BeforeEach
    public void iniciar(){
        System.out.println("@BeforeEach");
    }

    // Me ejecuto después de cada metodo de prueba
    @AfterEach
    public void terminar(){
        System.out.println("@AfterEach");
    }

    @Test
    public void testArea(){
        Rectangulo r = new Rectangulo(3,4);
        assertEquals(12, r.area(), "El area (3,4) debe ser 12");
        // assertTrue(r.area()==12, "El area debería teber valor = 12");
    }

    @Test
    public void testPerimetro(){
        Rectangulo r = new Rectangulo(3,4);
        assertEquals(14, r.perimetro(), "El perímetro (3,4) debe ser 14");
    }
/*
    @Test
    void grupoAserciones() {
        Rectangulo r = new Rectangulo(3,4);
        // En un grupo de aserciones se ejecutan todas ellas
        // y ser reportan todas los fallos juntos
    assertAll(
            () -> assertEquals(12, r.area(), "El area (3,4) debe ser 12"),
            () -> assertEquals(14, r.perimetro(), "El perímetro (3,4) debe ser 14"));
    }

 */
}
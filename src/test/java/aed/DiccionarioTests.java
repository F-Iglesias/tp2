package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class DiccionarioTests {
   
    @Test 
    void nuevo_diccionario_vacio() {
        Diccionario<Integer, Integer> diccionario = new Diccionario<>();
        assertFalse(diccionario.pertenece(12));
        assertEquals(diccionario.cardinal(), 0);
    }


    @Test
    void definir_una_clave()
    {
        Diccionario<Integer, Integer> diccionario = new Diccionario<>();
        diccionario.definir(1, 2);
        assertEquals(1, diccionario.cardinal());
        assertEquals(2, diccionario.obtener(1));
        assertTrue(diccionario.pertenece(1));
    }


    @Test 
    void definir_varias_claves()
    {
        Diccionario<Integer, Integer> diccionario = new Diccionario<>();
        diccionario.definir(1, 2);
        diccionario.definir(2, 3);
        diccionario.definir(3, 5);
        diccionario.definir(4, 7);
        diccionario.definir(5, 11);

        assertTrue(diccionario.pertenece(1));
        assertTrue(diccionario.pertenece(2));
        assertTrue(diccionario.pertenece(3));
        assertTrue(diccionario.pertenece(4));
        assertTrue(diccionario.pertenece(5));
        
        assertEquals(2, diccionario.obtener(1));
        assertEquals(3, diccionario.obtener(2));
        assertEquals(5, diccionario.obtener(3));
        assertEquals(7, diccionario.obtener(4));
        assertEquals(11, diccionario.obtener(5));

        assertEquals(diccionario.cardinal(), 5);

    }

    @Test
    void redefinir_misma_clave()
    {
        Diccionario<Integer, Integer> diccionario = new Diccionario<>();
        
        diccionario.definir(4, 9);
        assertEquals(1, diccionario.cardinal());
        assertTrue(diccionario.pertenece(4));

        diccionario.definir(4, 9);
        assertEquals(1, diccionario.cardinal());
        assertTrue(diccionario.pertenece(4));

        diccionario.definir(4, 13);
        assertEquals(1, diccionario.cardinal());
        assertTrue(diccionario.pertenece(4));

        diccionario.definir(4, 20);
        assertEquals(1, diccionario.cardinal());
        assertTrue(diccionario.pertenece(4));

        diccionario.definir(4, 3);
        assertEquals(1, diccionario.cardinal());
        assertTrue(diccionario.pertenece(4));

    }


    @Test
    void insertar_y_borrar_una_clave()
    {
        Diccionario<Integer, Integer> diccionario = new Diccionario<>();
        diccionario.definir(1, 1);
        assertTrue(diccionario.pertenece(1));
        assertEquals(1, diccionario.cardinal());

        diccionario.borrar(1);
        assertFalse(diccionario.pertenece(1));
        assertEquals(0, diccionario.cardinal());

    }


    @Test 
    void insertar_y_borrar_varias_claves()
    {
        Diccionario<Integer, Integer> diccionario = new Diccionario<>();
        diccionario.definir(17, 5);
        diccionario.definir(42, -1);
        diccionario.definir(58, 3);
        diccionario.definir(59, 13);

        assertEquals(4, diccionario.cardinal());

        diccionario.borrar(17);
        diccionario.borrar(42);
        assertEquals(2, diccionario.cardinal());
        diccionario.borrar(42);
        assertEquals(2, diccionario.cardinal());


        diccionario.borrar(58);
        diccionario.borrar(59);
        assertEquals(0, diccionario.cardinal());

        diccionario.borrar(59);
        assertEquals(0, diccionario.cardinal());

    }

    @Test
    void crear_de_tuplas_ordenadas_por_id() {
        Tupla<Integer, Integer>[] tuplas = (Tupla<Integer, Integer>[]) new Tupla[100];
        for (int i = 0; i < 100; i++) {
            tuplas[i] = new Tupla(i, i*i);
        } 

        Diccionario<Integer, Integer> diccionario = new Diccionario<>(tuplas);

        assertEquals(100, diccionario.cardinal());
        for (int i = 0; i < 100; i++)
        {
            assertTrue(diccionario.pertenece(i));
            assertEquals(i*i, diccionario.obtener(i));
        }

    }

    @Test
    void obtener_tuplas_de_diccionario()
    {
        Diccionario<Integer, Integer> diccionario = new Diccionario<>();
        for (int i = 0; i < 100; i++)
        {
            diccionario.definir(i, i*i);
        }

        Tupla<Integer, Integer>[] tuplas = (Tupla<Integer, Integer>[]) new Tupla[100];
        diccionario.volcarTuplas(tuplas);

        for (int i = 0; i < 100; i++)
        {
            assertEquals(new Tupla<Integer, Integer>(i, i*i), tuplas[i]);
        }
    }


}

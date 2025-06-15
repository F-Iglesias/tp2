package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class ConjuntoTests {
    @Test
    void nuevo_conjunto_vacio() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();

        assertFalse(conjunto.pertenece(42));
        assertEquals(0, conjunto.cardinal());
    }

    @Test
    void insertar_un_elemento() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();

        conjunto.insertar(42);

        assertEquals(1, conjunto.cardinal());
        assertTrue(conjunto.pertenece(42));
        assertTrue(conjunto.estaBalanceado());
    }

    @Test
    void insertar_izquierda() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();

        conjunto.insertar(43);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(42);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(41);
        assertTrue(conjunto.estaBalanceado());

        assertTrue(conjunto.pertenece(43));
        assertTrue(conjunto.pertenece(42));
        assertTrue(conjunto.pertenece(41));
        assertEquals(3, conjunto.cardinal());
        assertTrue(conjunto.estaBalanceado());
    }

    @Test
    void insertar_derecha() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();

        conjunto.insertar(43);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(44);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(45);
        assertTrue(conjunto.estaBalanceado());

        assertTrue(conjunto.pertenece(43));
        assertTrue(conjunto.pertenece(44));
        assertTrue(conjunto.pertenece(45));
        assertEquals(3, conjunto.cardinal());
    }

    @Test
    void insertar_elemento_repetido() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();

        /*
         * ___2___
         * _1 _ 5_
         * . . 3 7
         * 
         */

        conjunto.insertar(2);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(1);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(5);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(3);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(7);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(3);
        assertTrue(conjunto.estaBalanceado());

        assertEquals(5, conjunto.cardinal());
        
        assertTrue(conjunto.pertenece(2));
        assertTrue(conjunto.pertenece(1));
        assertTrue(conjunto.pertenece(5));
        assertTrue(conjunto.pertenece(3));
        assertTrue(conjunto.pertenece(7));

        assertFalse(conjunto.pertenece(4));
    }

    @Test
    void insertar_cinco_nombres() {
        Conjunto<String> conjunto = new Conjunto<String>();

        // Todos los tipos de datos "Comparables" tienen el método compareTo()
        assertTrue("Jujuy".compareTo("La Pampa") < 0);
        assertTrue("Jujuy".compareTo("Chubut") > 0);

        conjunto.insertar("La Pampa");
        assertTrue(conjunto.estaBalanceado());
        assertEquals(1, conjunto.cardinal());
        assertEquals("La Pampa", conjunto.minimo());
        assertEquals("La Pampa", conjunto.maximo());

        conjunto.insertar("Chubut");
        assertTrue(conjunto.estaBalanceado());
        assertEquals(2, conjunto.cardinal());
        assertEquals("Chubut", conjunto.minimo());
        assertEquals("La Pampa", conjunto.maximo());

        conjunto.insertar("Formosa");
        assertTrue(conjunto.estaBalanceado());
        assertEquals(3, conjunto.cardinal());
        assertEquals("Chubut", conjunto.minimo());
        assertEquals("La Pampa", conjunto.maximo());

        conjunto.insertar("Catamarca");
        assertTrue(conjunto.estaBalanceado());
        assertEquals(4, conjunto.cardinal());
        assertEquals("Catamarca", conjunto.minimo());
        assertEquals("La Pampa", conjunto.maximo());

        conjunto.insertar("Jujuy");
        assertTrue(conjunto.estaBalanceado());
        assertEquals(5, conjunto.cardinal());
        assertEquals("Catamarca", conjunto.minimo());
        assertEquals("La Pampa", conjunto.maximo());

        assertTrue(conjunto.pertenece("Catamarca"));
        assertFalse(conjunto.pertenece("Buenos Aires"));
        assertTrue(conjunto.pertenece("Jujuy"));
    }

    @Test
    void eliminar_elemento_con_un_descendiente() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();

        conjunto.insertar(5);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(6);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(7);
        assertTrue(conjunto.estaBalanceado());
        conjunto.eliminar(6);
        assertTrue(conjunto.estaBalanceado());

        assertFalse(conjunto.pertenece(6));
        assertEquals(2, conjunto.cardinal());
        assertEquals(5, conjunto.minimo());
        assertEquals(7, conjunto.maximo());
    }

    @Test
    void eliminar_elemento_con_dos_descendientes() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();

        conjunto.insertar(5);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(4);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(7);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(6);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(8);
        assertTrue(conjunto.estaBalanceado());
        conjunto.eliminar(7);
        assertTrue(conjunto.estaBalanceado());

        assertFalse(conjunto.pertenece(7));
        assertEquals(4, conjunto.cardinal());
        assertEquals(4, conjunto.minimo());
        assertEquals(8, conjunto.maximo());
    }

    @Test
    void eliminar_raiz_cons_dos_hijos() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();

        conjunto.insertar(5);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(4);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(7);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(6);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(8);
        assertTrue(conjunto.estaBalanceado());
        conjunto.eliminar(5);
        assertTrue(conjunto.estaBalanceado());

        assertFalse(conjunto.pertenece(5));
        assertEquals(4, conjunto.cardinal());
        assertEquals(4, conjunto.minimo());
        assertEquals(8, conjunto.maximo());
    }

    @Test
    void eliminar_raiz_con_derecho() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();


        conjunto.insertar(6);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(8);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(9);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(7);
        assertTrue(conjunto.estaBalanceado());

        conjunto.eliminar(6);

        assertFalse(conjunto.pertenece(6));
        assertTrue(conjunto.pertenece(8));
        assertTrue(conjunto.pertenece(7));
        assertTrue(conjunto.pertenece(9));

        assertEquals(3, conjunto.cardinal());
        assertEquals(7, conjunto.minimo());
        assertEquals(9, conjunto.maximo());
    }

    @Test
    void eliminar_raiz_con_hijo_izquierdo() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();


        conjunto.insertar(6);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(4);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(2);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(5);
        assertTrue(conjunto.estaBalanceado());

        conjunto.eliminar(6);

        assertFalse(conjunto.pertenece(6));
        assertTrue(conjunto.pertenece(4));
        assertTrue(conjunto.pertenece(2));
        assertTrue(conjunto.pertenece(5));

        assertEquals(3, conjunto.cardinal());
        assertEquals(2, conjunto.minimo());
        assertEquals(5, conjunto.maximo());
    }

    @Test
    void eliminar_elemento_con_doble_descendencia() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();

        conjunto.insertar(5);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(4);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(20);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(15);
        assertTrue(conjunto.estaBalanceado());
        
        assertEquals(true, conjunto.estaBalanceado());
        conjunto.insertar(12);


        conjunto.insertar(16);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(24);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(22);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(25);
        assertTrue(conjunto.estaBalanceado());
        conjunto.eliminar(20);
        assertTrue(conjunto.estaBalanceado());

        assertFalse(conjunto.pertenece(20));
        assertEquals(8, conjunto.cardinal());
        assertEquals(4, conjunto.minimo());
        assertEquals(25, conjunto.maximo());
    }

    @Test
    void eliminar_elemento_con_sucesor_arriba() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();

        conjunto.insertar(5);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(4);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(20);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(15);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(12);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(24);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(22);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(25);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(19);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(21);
        assertTrue(conjunto.estaBalanceado());
        conjunto.eliminar(20);
        assertTrue(conjunto.estaBalanceado());

        assertFalse(conjunto.pertenece(20));
        assertEquals(9, conjunto.cardinal());
        assertEquals(4, conjunto.minimo());
        assertEquals(25, conjunto.maximo());
    }
 
    @Test 
    void construir_desde_lista_ordenada() {
        Conjunto<Integer> conjunto = new Conjunto<Integer>();
        Integer[] lista_ordenada = {3, 4, 7, 10, 11, 34, 87, 101};
        conjunto = new Conjunto<Integer>(lista_ordenada);

        for (int i : lista_ordenada) {
            assertTrue(conjunto.pertenece(i));
        }

        assertEquals(conjunto.cardinal(), lista_ordenada.length);
        assertTrue(conjunto.estaBalanceado());

    }

    @Test
    void volcarElementos() {
        Conjunto<Integer> conjunto = new Conjunto<>();
        conjunto.insertar(5);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(4);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(20);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(15);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(12);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(16);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(24);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(22);
        assertTrue(conjunto.estaBalanceado());
        conjunto.insertar(25);
        assertTrue(conjunto.estaBalanceado());

        Integer[] elems = new Integer[9];
        conjunto.volcarElementos(elems);
        Integer[] elems_esperados = {4, 5, 12, 15, 16, 20, 22, 24, 25};
        for (int i = 0; i < elems.length; i++)
        {
            assertEquals(elems_esperados[i], elems[i]);
        }
    }

    @Test
    void conjunto_vacio_toString() {
        Conjunto<Integer> c = new Conjunto<Integer>();

        assertEquals("{}", c.toString());

    }

    @Test
    void conjunto_de_un_elemento_toString() {
        Conjunto<Integer> c = new Conjunto<Integer>();

        c.insertar(7);

        assertEquals("{7}", c.toString());

    }

    @Test
    void conjunto_de_muchos_numeros_toString() {
        Conjunto<Integer> c = new Conjunto<Integer>();
        c.insertar(5);
        c.insertar(4);
        c.insertar(7);
        c.insertar(6);
        c.insertar(8);
        assertEquals("{4,5,6,7,8}", c.toString());
        c.eliminar(5);
        c.eliminar(7);
        assertEquals("{4,6,8}", c.toString());

    }

    Integer NCLAVES = 1000;

    private Integer clave(Integer i) {
        return NCLAVES * ((i * i - 100 * i) % NCLAVES) + i;
    }

    @Test
    void stress() {

        Conjunto<Integer> conjunto = new Conjunto<Integer>();

        // Insertar
        for (Integer i = 0; i < NCLAVES; i++) {
            Integer k = clave(i);
            assertEquals(i, conjunto.cardinal());
            assertEquals(false, conjunto.pertenece(k));
            conjunto.insertar(k);
            assertEquals(true, conjunto.pertenece(k));
            assertTrue(conjunto.estaBalanceado());
        }
        assertEquals(NCLAVES, conjunto.cardinal());
        assertEquals(true, conjunto.estaBalanceado());
        // Insertar de nuevo
        for (Integer i = 0; i < NCLAVES; i++) {
            Integer k = clave(i);
            assertTrue(conjunto.pertenece(k));

            conjunto.insertar(k);

            assertTrue(conjunto.pertenece(k));
            assertEquals(NCLAVES, conjunto.cardinal());
            assertTrue(conjunto.estaBalanceado());
        }

        assertEquals(true, conjunto.estaBalanceado());
        // Eliminar los valores para i par
        for (Integer i = 0; i < NCLAVES; i++) {
            Integer k = clave(i);
            assertTrue(conjunto.pertenece(k));
            if (i % 2 == 0) {
                conjunto.eliminar(k);

                assertFalse(conjunto.pertenece(k));
            }
            assertTrue(conjunto.estaBalanceado());
        }
        assertEquals(NCLAVES / 2, conjunto.cardinal());
        assertEquals(true, conjunto.estaBalanceado());

        // Eliminar los valores para i impar
        for (Integer i = 0; i < NCLAVES; i++) {
            Integer k = clave(i);
            if (i % 2 == 0) {
                assertFalse(conjunto.pertenece(k));
            } else {
                assertTrue(conjunto.pertenece(k));
                conjunto.eliminar(k);

                assertFalse(conjunto.pertenece(k));
            }
            assertTrue(conjunto.estaBalanceado());
        }
        assertEquals(0, conjunto.cardinal());
        assertEquals(true, conjunto.estaBalanceado());
        // Verificar que no haya valores
        for (Integer i = 0; i < NCLAVES; i++) {
            Integer k = clave(i);
            assertFalse(conjunto.pertenece(k));
            assertTrue(conjunto.estaBalanceado());
        }
    }
}

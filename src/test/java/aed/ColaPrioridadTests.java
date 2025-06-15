package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ColaPrioridadTests
{
    @Test
    void desencolarOrdenaLista()
    {
        Integer[] elems = {1, 6, 19, 3, -6, 20, 800, 4, 727, 1999, -399};
        Integer[] elemsOrdenados = {1999, 800, 727, 20, 19, 6, 4, 3, 1, -6, -399};
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(elems);
        
        for (int i = 0; i < elemsOrdenados.length; i++)
        {
            assertFalse(cola.estaVacia());
            assertEquals(elemsOrdenados[i], cola.desencolar());
        }
        assertTrue(cola.estaVacia());


    }

    @Test
    void desencolarOrdenaListaGrande() {
        Integer[] elems = new Integer[10000];
        for (int i = 0; i < elems.length; i++)
        {
            if (i <= (elems.length-1)/2) { elems[i] = 2*i; }
            else { elems[i] = 2*(i - (elems.length-1)/2 ) - 1; }
        }

        ColaPrioridad<Integer> cola = new ColaPrioridad<>(elems);

        for (int i = 0; i < elems.length; i++)
        {
            assertFalse(cola.estaVacia());
            assertEquals(elems.length-1-i, cola.desencolar());
        }

        assertTrue(cola.estaVacia());
    }
}

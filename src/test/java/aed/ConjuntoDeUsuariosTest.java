package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConjuntoDeUsuariosTest {
    @Test
    void prueba1() {
        int n_usuarios = 10;
        ConjuntoDeUsuarios usuarios = new ConjuntoDeUsuarios(n_usuarios);
        assertEquals(usuarios.maximoTenedor(), new Usuario(1, 0));

        usuarios.agregarSaldo(4, 10);
        usuarios.agregarSaldo(3, 6);
        usuarios.agregarSaldo(3, 7);
        assertEquals(usuarios.maximoTenedor(), new Usuario(3, 13));
        
        usuarios.procesarTransaccion(new Transaccion(23, 3, 4, 6));
        assertEquals(usuarios.saldo(3), 7);
        assertEquals(usuarios.saldo(4), 16);
        assertEquals(usuarios.maximoTenedor(), new Usuario(4, 16));

        usuarios.revertirTransaccion(new Transaccion(23, 3, 4, 6));
        assertEquals(usuarios.saldo(3), 13);
        assertEquals(usuarios.saldo(4), 10);
        assertEquals(usuarios.maximoTenedor(), new Usuario(3, 13));


    }

    @Test
    void prueba2() {
        Integer[] elems = {1, 6, 19, 3, -6, 20, 800, 4, 727, 1999, -399};
        Integer[] elemsOrdenados = {1999, 800, 727, 20, 19, 6, 4, 3, 1, -6, -399};
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(elems);
        
        for (int i = 0; i < elemsOrdenados.length -1; i++)
        {
            
            assertEquals(elemsOrdenados[i], cola.desencolar());
        }


    }


    @Test
    void prueba3() {
        Integer[] elems = {1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] elemsMayorAMenor = {8, 7, 6, 5, 4, 3, 2, 1};
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(elems);

        for (int i = 0; i < elems.length; i++) {
            assertEquals(elemsMayorAMenor[i], cola.desencolar());
        }
    }
    
    @Test
    void prueba4() {
        Diccionario<Integer, Integer> dict = new Diccionario<>();
        dict.definir(1, 2);
        assertEquals(true, dict.pertenece(1));
        assertEquals(false, dict.pertenece(2));

        assertEquals(2, dict.obtener(1));
        dict.borrar(1);
        assertEquals(false, dict.pertenece(1));
    }
    
}

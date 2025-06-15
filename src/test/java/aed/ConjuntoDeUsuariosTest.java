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
        ConjuntoDeUsuarios usuarios = new ConjuntoDeUsuarios(3);
        usuarios.agregarSaldo(2, 10);
        Transaccion t1 = new Transaccion(1, 2, 3, 5);
        Transaccion t2 = new Transaccion(2, 2, 3, 15);
        Transaccion t3 = new Transaccion(3, 0, 3, 100);

        assertEquals(true, usuarios.esTransaccionValida(t1));
        assertEquals(false, usuarios.esTransaccionValida(t2));
        assertEquals(true, usuarios.esTransaccionValida(t3));
    }
    
}

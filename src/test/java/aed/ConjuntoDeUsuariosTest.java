package aed;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ConjuntoDeUsuariosTest {
    @Test 
    void maximo_tenedor_nuevo_conjunto() {
        ConjuntoDeUsuarios usuarios = new ConjuntoDeUsuarios(100);
        assertEquals(new Usuario(1, 0), usuarios.maximoTenedor());
    }

    @Test 
    void agregar_saldo_un_usuario() {
        ConjuntoDeUsuarios usuarios = new ConjuntoDeUsuarios(100);
        usuarios.agregarSaldo(4, 10);
        assertEquals(10, usuarios.saldo(4));
        usuarios.agregarSaldo(4, 5);
        assertEquals(15, usuarios.saldo(4));
    }

    @Test 
    void agregar_saldo_afecta_maximo_tenedor()
    {
       ConjuntoDeUsuarios usuarios = new ConjuntoDeUsuarios(100); 
       usuarios.agregarSaldo(4, 10);
       assertEquals(new Usuario(4, 10), usuarios.maximoTenedor());
       usuarios.agregarSaldo(20, 800);
       assertEquals(new Usuario(20, 800), usuarios.maximoTenedor());
       usuarios.agregarSaldo(20, -794);
       assertEquals(new Usuario(4, 10), usuarios.maximoTenedor());
    }

    @Test 
    void procesar_una_transaccion() {
        ConjuntoDeUsuarios usuarios = new ConjuntoDeUsuarios(100); 
        Transaccion t = new Transaccion(5, 10, 20, 800);
        usuarios.procesarTransaccion(t);
        assertEquals(800, usuarios.saldo(20));
        assertEquals(-800, usuarios.saldo(10));
    }

    @Test 
    void procesar_transaccion_afecta_maximo_tenedor()
    {
        ConjuntoDeUsuarios usuarios = new ConjuntoDeUsuarios(100); 
        usuarios.agregarSaldo(4, 1000);
        Transaccion t = new Transaccion(1, 4, 10, 501);
        assertEquals(new Usuario(4, 1000), usuarios.maximoTenedor());

        usuarios.procesarTransaccion(t);
        assertEquals(new Usuario(10, 501), usuarios.maximoTenedor());
    }

    @Test 
    void revertir_una_transaccion()
    {
        ConjuntoDeUsuarios usuarios = new ConjuntoDeUsuarios(100); 
        usuarios.agregarSaldo(4, 1000);
        usuarios.agregarSaldo(10, 5);
        Transaccion t = new Transaccion(1, 4, 10, 501);   
        usuarios.procesarTransaccion(t);
        usuarios.revertirTransaccion(t);
        assertEquals(1000, usuarios.saldo(4));
        assertEquals(5, usuarios.saldo(10));
    }

    @Test 
    void revertir_transaccion_afecta_maximo_tenedor()
    {
        ConjuntoDeUsuarios usuarios = new ConjuntoDeUsuarios(100); 
        usuarios.agregarSaldo(14, 2501);
        usuarios.agregarSaldo(19, 3000);
        Transaccion t = new Transaccion(1, 14, 19, 500);
        assertEquals(new Usuario(19, 3000), usuarios.maximoTenedor());
        usuarios.revertirTransaccion(t);
        assertEquals(new Usuario(14, 3001), usuarios.maximoTenedor());
    }

    @Test 
    void transaccion_valida()
    {
        ConjuntoDeUsuarios usuarios = new ConjuntoDeUsuarios(100); 

        usuarios.agregarSaldo(2, 99);

        Transaccion t1 = new Transaccion(1, 2, 4, 100);
        Transaccion t2 = new Transaccion(1, 2, 4, 50);
        Transaccion t3 = new Transaccion(1, 2, 4, -10);
        Transaccion t4 = new Transaccion(1, 2, 4, 0);

        Transaccion t5 = new Transaccion(1, 2, -3, 5);

        assertFalse(usuarios.esTransaccionValida(t1));
        assertTrue(usuarios.esTransaccionValida(t2));
        assertFalse(usuarios.esTransaccionValida(t3));
        assertFalse(usuarios.esTransaccionValida(t4));
        assertFalse(usuarios.esTransaccionValida(t5));
        
    }

    
}

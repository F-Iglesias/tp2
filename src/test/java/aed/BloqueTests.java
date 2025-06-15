package aed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BloqueTests {

    @Test
    void bloqueVacio() {
        Bloque bloque = new Bloque();
        assertEquals(0, bloque.transacciones().length);
        assertEquals(0, bloque.montoMedio());
    }

    @Test
    void bloqueSoloCreacion() {
        Transaccion creacion = new Transaccion(0, 0, 2, 10);
        Bloque bloque = new Bloque(new Transaccion[]{creacion});
        Transaccion[] txs = bloque.transacciones();
        assertEquals(1, txs.length);
        assertEquals(creacion, txs[0]);
        assertEquals(creacion, bloque.maximaTransaccion());
        assertEquals(0, bloque.montoMedio());
    }

    @Test
    void maximaTransaccionEmpateMontoMayorId() {
        Transaccion t1 = new Transaccion(0, 0, 2, 10);
        Transaccion t2 = new Transaccion(1, 2, 3, 8);
        Transaccion t3 = new Transaccion(2, 3, 4, 18);
        Bloque bloque = new Bloque(new Transaccion[]{t1, t2, t3});
        assertEquals(t3, bloque.maximaTransaccion());
    }

    @Test
    void borrarTransaccionMaxima() {
        Transaccion t1 = new Transaccion(0, 0, 2, 10);
        Transaccion t2 = new Transaccion(1, 2, 3, 5);
        Transaccion t3 = new Transaccion(2, 3, 4, 17);
        Bloque bloque = new Bloque(new Transaccion[]{t1, t2, t3});
        Transaccion max = bloque.borrarTransaccionMaxima();
        assertEquals(t3, max);
        Transaccion[] txs = bloque.transacciones();
        assertEquals(2, txs.length);
        assertEquals(t1, txs[0]);
        assertEquals(t2, txs[1]);
        assertEquals(t1, bloque.maximaTransaccion());
        assertEquals(5, bloque.montoMedio());
    }

    @Test
    void borrarHastaVacio() {
        Transaccion t1 = new Transaccion(0, 0, 2, 10);
        Transaccion t2 = new Transaccion(1, 2, 3, 5);
        Bloque bloque = new Bloque(new Transaccion[]{t1, t2});
        bloque.borrarTransaccionMaxima();
        bloque.borrarTransaccionMaxima();
        assertEquals(0, bloque.transacciones().length);
        assertEquals(0, bloque.montoMedio());
    }

    @Test
    void noMutarInput() {
        Transaccion t1 = new Transaccion(0, 0, 2, 10);
        Transaccion t2 = new Transaccion(1, 2, 3, 5);
        Transaccion[] original = new Transaccion[]{t1, t2};
        Bloque bloque = new Bloque(original);
        bloque.borrarTransaccionMaxima();
        assertEquals(2, original.length);
        assertEquals(t1, original[0]);
        assertEquals(t2, original[1]);
    }

    @Test
    void bloqueConTransaccionesConMismoMontoYId() {
        Transaccion t1 = new Transaccion(0, 0, 2, 10);
        Transaccion t2 = new Transaccion(0, 2, 3, 10);
        Bloque bloque = new Bloque(new Transaccion[]{t1, t2});
        assertEquals(t1, bloque.maximaTransaccion());
    }

    @Test
    void bloqueConUnaTransaccion() {
        Transaccion t1 = new Transaccion(5, 1, 2, 20);
        Bloque bloque = new Bloque(new Transaccion[]{t1});
        assertEquals(1, bloque.transacciones().length);
        assertEquals(t1, bloque.maximaTransaccion());
        assertEquals(20, bloque.montoMedio());
        assertEquals(t1, bloque.borrarTransaccionMaxima());
        assertEquals(0, bloque.transacciones().length);
    }

    @Test
    void borrarTransaccionMaximaConEmpate() {
        Transaccion t1 = new Transaccion(1, 0, 2, 10);
        Transaccion t2 = new Transaccion(2, 2, 3, 10);
        Bloque bloque = new Bloque(new Transaccion[]{t1, t2});
        assertEquals(t2, bloque.borrarTransaccionMaxima());
        assertEquals(t1, bloque.maximaTransaccion());
    }
}
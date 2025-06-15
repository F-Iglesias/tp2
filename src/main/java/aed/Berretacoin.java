package aed;

public class Berretacoin {

    ConjuntoDeUsuarios usuarios;
    Bloque ultimoBloque;

    public Berretacoin(int n_usuarios) // O(P)
    {
        usuarios = new ConjuntoDeUsuarios(n_usuarios); // O(P)
        ultimoBloque = new Bloque(); // O(1)
    }


    public void agregarBloque(Transaccion[] transacciones) // O(n_b log P)
    {
        /* Tenemos, en el peor caso, n_b iteraciones de costo O(log P), así que evaluar este bucle
         * tiene complejidad temporal O(n_b log P)
         */
        for (Transaccion t : transacciones)
        {
            // Descartamos toda transacción en la que el comprador (si es usuario) gasta más de lo que tiene
            if (usuarios.esTransaccionValida(t)) { usuarios.procesarTransaccion(t); } // O(log P)
        }

        ultimoBloque = new Bloque(transacciones); // O(n_b)

        // El costo total es O(n_b log P) + O(n_b) = O(n_b log P)
    }

    public Transaccion txMayorValorUltimoBloque() // O(1)
    {
        return ultimoBloque.maximaTransaccion(); // O(1)
    }

    public Transaccion[] txUltimoBloque() // O(n_b)
    {
        return ultimoBloque.transacciones(); // O(n_b)
    }

    public int maximoTenedor() // O(1)
    {
        Usuario maximoTenedor = usuarios.maximoTenedor(); // O(1)
        return maximoTenedor.id(); // O(1)
    }

    public int montoMedioUltimoBloque() // O(1)
    {
        return ultimoBloque.montoMedio(); // O(1)
    }

    public void hackearTx() // O(log n_b + log P)
    {
        Transaccion t = ultimoBloque.borrarTransaccionMaxima(); // log n_b
        usuarios.revertirTransaccion(t); // log P
    }
}

package aed;

public class ConjuntoDeUsuarios {
    
    private Usuario maximoTenedor; // Dentro de todos los usuarios con saldo máximo, es el que tiene la menor id.
    
    private Conjunto<Usuario> usuarios;  
    private int[] saldos; // array que contiene todos los saldos ordenados por id.

    

    public ConjuntoDeUsuarios(int n_usuarios) // O(P)
    {

        saldos = new int[n_usuarios]; // O(P)
        Usuario[] array_usuarios = new Usuario[n_usuarios]; // O(P)
        
        for (int i = 0; i < n_usuarios; i++) // P operaciones O(1), el bucle tiene costo O(P)
        { 
            saldos[i] = 0;
            array_usuarios[i] = new Usuario(n_usuarios - i, 0); 
        }

        // La lista Usuario(n_usuarios, 0), ..., Usuario(1, 0) está ordenada así que podemos construir usuarios en tiempo lineal
        usuarios = new Conjunto<Usuario>(array_usuarios); // O(P)

        maximoTenedor = new Usuario(1, 0); // O(1)

    }

    public void agregarSaldo(int id, int monto) // O(log P)
    {
        int saldoAnterior = saldos[id-1]; // O(1)  
        int saldoNuevo = saldoAnterior + monto; // O(1)
        saldos[id-1] = saldoNuevo; // O(1)
        usuarios.eliminar(new Usuario(id, saldoAnterior)); // O(log P)
        usuarios.insertar(new Usuario(id, saldoNuevo)); // O(log P)
        maximoTenedor = usuarios.maximo(); // O(log P)
    }

    // Le quita el monto de la transacción al comprador y se lo añade al vendedor
    public void procesarTransaccion(Transaccion t) // O(log P) 
    {
        // La id 0 en el comprador representa transacciones de creación y no es un usuario.
        if (t.id_comprador() != 0) { agregarSaldo(t.id_comprador(), -t.monto()); }
        agregarSaldo(t.id_vendedor(), t.monto());
    }

    /* 
     * Le quita el monto al vendedor y se lo devuelve al comprador.
     */
    public void revertirTransaccion(Transaccion t) // O(log P)
    {
        if (t.id_comprador() != 0) { agregarSaldo(t.id_comprador(), t.monto());}
        agregarSaldo(t.id_vendedor(), -t.monto());
    }

    public Usuario maximoTenedor() {
        return new Usuario(maximoTenedor); // Para evitar aliasing
    }


    public int saldo(int id) {
        return saldos[id-1];
    }
}

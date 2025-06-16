package aed;

public class ConjuntoDeUsuarios {
    
    private Usuario maximoTenedor; // Dentro de todos los usuarios con saldo máximo, es el que tiene la menor id.
    
    private Conjunto<Usuario> usuarios; // Conjunto con todos los usuarios implementado sobre un arbol AVL  
    private int[] saldos; // array que contiene todos los saldos ordenados por id.
    
    
    public ConjuntoDeUsuarios(int n_usuarios) // O(P)
    {

        saldos = new int[n_usuarios]; // O(P)
        Usuario[] array_usuarios = new Usuario[n_usuarios]; // O(P)
        
        for (int i = 0; i < n_usuarios; i++) // P operaciones O(1), el bucle tiene costo O(P)
        { 
            saldos[i] = 0; // O(1)
            array_usuarios[i] = new Usuario(n_usuarios - i, 0); // O(1)
        }

        // La lista Usuario(n_usuarios, 0), ..., Usuario(1, 0) está ordenada así que podemos construir usuarios en tiempo lineal
        usuarios = new Conjunto<Usuario>(array_usuarios); // O(P)

        maximoTenedor = new Usuario(1, 0); // O(1)

    }


    private boolean esUsuario(int id) // O(1)
    {
        return 0<id && id <= usuarios.cardinal(); 
    }

    // Devuelve true si, que el comprador sea usuario implica que no gasta más de lo que tiene.
    // De esta forma, las transacciones de creación son siempre válidas.
    public boolean esTransaccionValida(Transaccion t) // O(1)
    {
        return (!esUsuario(t.id_comprador()) || saldo(t.id_comprador()) >= t.monto()) && t.monto() > 0 && esUsuario(t.id_vendedor());
    }

    // Le agrega un monto específico a un usuario. No le preocupa si el usuario termina con saldo negativo.
    public void agregarSaldo(int id, int monto) // O(log P)
    {
        if (!esUsuario(id)) { return; } // O(1)
        int saldoAnterior = saldos[id-1]; // O(1)  
        int saldoNuevo = saldoAnterior + monto; // O(1)
        saldos[id-1] = saldoNuevo; // O(1)
        usuarios.eliminar(new Usuario(id, saldoAnterior)); // O(log P)
        usuarios.insertar(new Usuario(id, saldoNuevo)); // O(log P)
        maximoTenedor = usuarios.maximo(); // O(log P)
    }

    // Le quita el monto de la transacción al comprador y se lo añade al vendedor. No le preocupa si el usuario termina con saldo negativo.
    public void procesarTransaccion(Transaccion t) // O(log P) 
    {
        // La id 0 en el comprador representa transacciones de creación y no es un usuario.
        agregarSaldo(t.id_comprador(), -t.monto()); // O(log P)
        agregarSaldo(t.id_vendedor(), t.monto()); // O(log P)
    }

    /* 
     * Le quita el monto al vendedor y se lo devuelve al comprador.
     * No le preocupa si el usuario termina con saldo negativo.
     */
    public void revertirTransaccion(Transaccion t) // O(log P)
    {
        agregarSaldo(t.id_comprador(), t.monto()); // O(log P)
        agregarSaldo(t.id_vendedor(), -t.monto()); // O(log P)
    }

    public Usuario maximoTenedor() // O(1) 
    {
        return new Usuario(maximoTenedor); // O(1). 
    }


    public int saldo(int id) // O(1) 
    {
        return saldos[id-1]; // O(1)
    }
}

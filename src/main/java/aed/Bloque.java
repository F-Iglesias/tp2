package aed;

public class Bloque {
    
    private ColaPrioridad<Transaccion> colaDeTransacciones;
    private Diccionario<Integer, Transaccion> transaccionesPorId;

    private int montos_nc; // La suma de todos los montos de las transacciones de no creación.
    private int ts_nc; // Cantidad de transacciones de no creación.

    // Bloque vacío
    public Bloque() // O(1)
    {
        colaDeTransacciones = new ColaPrioridad<>(new Transaccion[0]); // Cola vacía
        transaccionesPorId = new Diccionario<>(); // Diccionario vacío
        montos_nc = 0;
        ts_nc = 0;
    }

    public boolean estaVacio() // O(1) 
    {
        return colaDeTransacciones.estaVacia();
    }

    // Dada una lista de transacciones, ordenadas por id, construye un bloque en tiempo lineal
    public Bloque(Transaccion[] ts) // O(n)
    {
        
        Tupla<Integer, Transaccion>[] array_tuplas = (Tupla<Integer, Transaccion>[]) new Tupla[ts.length]; // O(n)

        Transaccion[] copia_ts = new Transaccion[ts.length]; // O(n)
        
        
        ts_nc = 0; // O(1)
        montos_nc = 0; // O(1)

        for (int i = 0; i < ts.length; i++) // O(n) iteraciones de costo constante. 
        {
            if (ts[i].id_comprador() != 0) // O(1)
            {  
                ts_nc += 1;
                montos_nc+= ts[i].monto();
            }
            array_tuplas[i] = new Tupla<>(ts[i].id(), new Transaccion(ts[i])); // O(1)
            copia_ts[i] = new Transaccion(ts[i]); // O(1)
        }



        transaccionesPorId = new Diccionario<>(array_tuplas); // O(n)
        colaDeTransacciones = new ColaPrioridad<>(copia_ts); // O(n)

    }

    // Devuelve una lista con todas las transacciones ordenadas por id
    public Transaccion[] transacciones() // O(n)
    {
        int n_tuplas = transaccionesPorId.cardinal(); // O(1)
        Transaccion[] transacciones = new Transaccion[n_tuplas]; // O(n)
        Tupla<Integer, Transaccion>[] array_tuplas = (Tupla<Integer, Transaccion>[]) new Tupla[n_tuplas]; // O(n)

        transaccionesPorId.volcarTuplas(array_tuplas); // O(n)



        for (int i = 0; i < transacciones.length; i++) // O(n) iteraciones de costo constante
        {
            transacciones[i] = new Transaccion(array_tuplas[i].valor); // O(1)
        }
        return transacciones; // O(1)
    }

    // Devuelve aquella transaccion de saldo máximo con la mayor id
    public Transaccion maximaTransaccion() // O(1)
    {
        return new Transaccion(colaDeTransacciones.maximo()); // O(1)
    }

    // El monto medio de todas las transacciones sin contar las de creación. Si no hay transacciones de no creación vale 0.
    public int montoMedio() // O(1)
    {
        if (ts_nc > 0) { return montos_nc/ts_nc; } // O(1)
        return 0; // O(1)
    }

    // Borra la transaccion máxima y devuelve su valor
    public Transaccion borrarTransaccionMaxima() // O(log n)
    {
        Transaccion maximaTransaccion = colaDeTransacciones.desencolar(); //O(log n)
        
        if (maximaTransaccion.id_comprador() != 0) // O(1)
        { 
            montos_nc -= maximaTransaccion.monto(); // O(1)
            ts_nc = ts_nc-1; // O(1)
        }

        transaccionesPorId.borrar(maximaTransaccion.id()); // O(log n)
        return new Transaccion(maximaTransaccion); // O(1)
    }
}

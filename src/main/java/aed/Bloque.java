package aed;

public class Bloque {
    
    private ColaPrioridad<Transaccion> colaDeTransacciones;
    private Diccionario<Integer, Transaccion> transaccionesPorId;

    private int sumaMontos;
    private int k; // Transacciones de no creación

    // Bloque vacío
    public Bloque() // O(1)
    {
        colaDeTransacciones = new ColaPrioridad<>(new Transaccion[0]);
        transaccionesPorId = new Diccionario<>();
    }


    // Dada una lista de transacciones, ordenadas por id, construye un bloque en tiempo lineal
    public Bloque(Transaccion[] ts) // O(n)
    {
        Tupla<Integer, Transaccion>[] array_tuplas = (Tupla<Integer, Transaccion>[]) new Tupla[ts.length];

        Transaccion[] copia_ts = new Transaccion[ts.length];
        
        
        k = 0;
        sumaMontos = 0;

        for (int i = 0; i < ts.length; i++)
        {
            if (ts[i].id_comprador() != 0) { 
                k += 1;
                sumaMontos+= ts[i].monto();
            }
            array_tuplas[i] = new Tupla<>(ts[i].id(), new Transaccion(ts[i]));
            copia_ts[i] = new Transaccion(ts[i]);
        }



        transaccionesPorId = new Diccionario<>(array_tuplas);
        colaDeTransacciones = new ColaPrioridad<>(copia_ts);

    }

    // Devuelve una lista con todas las transacciones ordenadas por id
    public Transaccion[] transacciones() // O(n)
    {
        int n_tuplas = transaccionesPorId.cardinal();
        Transaccion[] transacciones = new Transaccion[n_tuplas];
        Tupla<Integer, Transaccion>[] array_tuplas = (Tupla<Integer, Transaccion>[]) new Tupla[n_tuplas];

        transaccionesPorId.volcarTuplas(array_tuplas);



        for (int i = 0; i < transacciones.length; i++)
        {
            transacciones[i] = new Transaccion(array_tuplas[i].valor); // Para evitar aliasing
        }
        return transacciones;
    }

    // Devuelve aquella transaccion de saldo máximo con la mayor id
    public Transaccion maximaTransaccion() // O(1)
    {
        return new Transaccion(colaDeTransacciones.maximo());
    }

    // El monto medio de todas las transacciones sin contar las de creación. Si no hay transacciones de no creación vale 0.
    public int montoMedio() // O(1)
    {
        if (k > 0) { return sumaMontos/k; }
        return 0;
    }

    // Borra la transaccion máxima y devuelve su valor
    public Transaccion borrarTransaccionMaxima() // O(log n)
    {
        Transaccion maximaTransaccion = colaDeTransacciones.desencolar();
        
        if (maximaTransaccion.id_comprador() != 0){
            sumaMontos -= maximaTransaccion.monto();
            k = k-1;
        }

        transaccionesPorId.borrar(maximaTransaccion.id());
        return new Transaccion(maximaTransaccion);
    }
}

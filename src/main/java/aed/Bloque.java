package aed;

public class Bloque {
    
    ColaPrioridad<Transaccion> colaDeTransacciones;
    Diccionario<Integer, Transaccion> transaccionesPorId;

    int montoMedio;
    int k; // Transacciones de no creación

    // Bloque vacío
    public Bloque() // O(1)
    {
        colaDeTransacciones = new ColaPrioridad<>();
        transaccionesPorId = new Diccionario<>();
    }


    // Dada una lista de transacciones, ordenadas por id, construye un bloque en tiempo lineal
    public Bloque(Transaccion[] ts) // O(n)
    {
        Integer[] ids = new Integer[ts.length];
        Transaccion[] copia1 = new Transaccion[ts.length];
        Transaccion[] copia2 = new Transaccion[ts.length];
        
        k = 0;
        montoMedio = 0;

        for (int i = 0; i < ts.length; i++)
        {
            if (ts[i].id_comprador() != 0) { 
                k += 1;
                montoMedio+= ts[i].monto();
            }
            ids[i] = ts[i].id();
            copia1[i] = new Transaccion(ts[i]);
            copia2[i] = new Transaccion(ts[i]);
        }

        if (k > 0)
        {
            montoMedio = montoMedio/k;
        }


        transaccionesPorId = new Diccionario<>();
        transaccionesPorId.construirDeClavesOrdenadasYValores(ids, copia1);
        colaDeTransacciones = new ColaPrioridad<>(copia2);

    }

    // Devuelve una lista con todas las transacciones ordenadas por id
    public Transaccion[] transacciones() // O(n)
    {
        Transaccion[] valores = transaccionesPorId.valoresOrdenadosPorClave();
        Transaccion[] transacciones = new Transaccion[valores.length];
        for (int i = 0; i < valores.length; i++)
        {
            transacciones[i] = new Transaccion(valores[i]);
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
        return montoMedio;
    }

    // Borra la transaccion máxima y devuelve su valor
    public Transaccion borrarTransaccionMaxima() // O(log n)
    {
        Transaccion maximaTransaccion = colaDeTransacciones.desencolar();
        
        if (maximaTransaccion.id_comprador() != 0){
            montoMedio = (montoMedio*k - maximaTransaccion.monto());
            if (k > 1) { montoMedio = montoMedio/(k-1); }
            k = k-1;
        }

        transaccionesPorId.borrar(maximaTransaccion.id());
        return new Transaccion(maximaTransaccion);
    }
}

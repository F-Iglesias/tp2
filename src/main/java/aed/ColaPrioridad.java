package aed;

public class ColaPrioridad<T extends Comparable<T>> {
    // Cola prioridad implementada sobre un max heap.


    // Cola vacía
    public ColaPrioridad()
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    /* 
     * Construye una cola prioridad a partir de una lista de elementos en tiempo lineal usando el algoritmo de Floyd.
     */
    public ColaPrioridad(T[] elems) // O(n)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public boolean estaVacia() // O(1)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public T maximo() // O(1)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    // No añado la operación encolar porque no la utilizamos en ninguna parte
    public T desencolar() // O(log n)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    

}

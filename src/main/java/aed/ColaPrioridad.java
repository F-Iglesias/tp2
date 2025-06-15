package aed;

public class ColaPrioridad<T extends Comparable<T>> {
    // Cola prioridad implementada sobre un max heap.

    private T[] heap;
    private int cardinal;

    private int hijoIzquierdo(int i) { return 2*i + 1; } // O(1)

    private int hijoDerecho(int i) { return 2*i + 2; } // O(1)


    private void intercambiar(int i, int j) // O(1)
    {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void heapificar(int i) // O(log n)
    {
        int mayor = i; // O(1)
        int izq = hijoIzquierdo(i); // O(1)
        int der = hijoDerecho(i); // O(1)

        /* Intercambiamos el nodo actual con alguno de sus hijos e iteramos en caso de ser necesario.
         * En el peor caso realizamos O(log n) iteraciones
        */
        if (der < cardinal && heap[i].compareTo(heap[der]) < 0 && heap[der].compareTo(heap[izq])>0) {
            mayor = der;
        }
        else if (izq < cardinal && heap[i].compareTo(heap[izq]) < 0) {
            mayor = izq;
        }
        
        if (mayor != i) {
            intercambiar(i, mayor);
            heapificar(mayor);
        }
    }

    /* 
     * Construye una cola prioridad a partir de una lista de elementos en tiempo lineal usando el algoritmo de Floyd.
     */
    public ColaPrioridad(T[] elems) // O(n)
    {
        heap = elems; // O(1)
        
        cardinal = elems.length; // O(1)

        // Algoritmo de Floyd. O(n)
        for (int i = cardinal/2 - 1; i >= 0; i--) {
            heapificar(i);
        }

    }

    public boolean estaVacia() // O(1)
    {
        return cardinal == 0;
    }

    
    public T maximo() // O(1)
    {
        return heap[0];
    }


    // No añado la operación encolar porque no la utilizamos en ninguna parte
    public T desencolar() // O(log n)
    {
        if (cardinal == 0) { throw new IllegalStateException("Heap vacío!!!"); }
        T res = heap[0]; // O(1)
        heap[0] = heap[(cardinal--) - 1]; // O(1). Reemplazamos el máximo elemento por el último y decrementamos cardinal
        heapificar(0); // O(log n)
        return res; // O(1)
    }


    

}

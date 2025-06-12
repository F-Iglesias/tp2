package aed;

public class ColaPrioridad<T extends Comparable<T>> {
    // Cola prioridad implementada sobre un max heap.

    private T[] heap;
    private int cardinal;
    private int capacidad;

    private int hijoIzquierdo(int i) { return 2*i + 1; }

    private int hijoDerecho(int i) { return 2*i + 2; }

    private void intercambiar(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void heapificar(int i) /* O(log n) */ {
        int mayor = i;
        int izq = hijoIzquierdo(i);
        int der = hijoDerecho(i);

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
        heap = elems;
        
        cardinal = elems.length;
        capacidad = elems.length;
        // Algoritmo de Floyd
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
        T res = heap[0];
        heap[0] = heap[(cardinal--) - 1]; // Reemplazamos el máximo elemento por el último y decrementamos cardinal
        heapificar(0);
        return res;
    }


    

}

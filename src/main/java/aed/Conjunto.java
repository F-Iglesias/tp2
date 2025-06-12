package aed;

public class Conjunto<T extends Comparable<T>> {
    
    // Conjunto implementado con un arbol AVL


    public Conjunto() // O(1)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public boolean pertenece() // O(log P)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public int cardinal() //O(1)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    // Devuelve el último valor hallado al aplicar búsqueda binaria sobre el arbol
    public T buscar(T elem) // O(log P)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    
    public void insertar(T elem) // O(log P) 
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public void eliminar(T elem) // O(log P)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public T minimo() // O(log P)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public T maximo() // O(log P)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    // Dada una lista ordenada, construye un arbol en tiempo lineal
    public T construirDeListaOrdenada(T[] elems) // O(P)
    {
        throw new UnsupportedOperationException("Implementar!");
    }


    /* 
     * PARA TESTING
     */

    public boolean estaBalanceado()
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public String toString()
    {
        throw new UnsupportedOperationException("Implementar!");
    }
}

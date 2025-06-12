package aed;

public class Diccionario<K extends Comparable<K>, V> {
    // Diccionario sobre AVL


    // Diccionario vacío
    public Diccionario()
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public boolean pertenece(K key) // O(log n)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public void definir(K k, V v) // O(log n)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public void borrar(K k) // O(log n)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public V obtener(K k) // O(log n)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    /*
     * Dada una lista ordenada de claves ks y una lista de valores vs del mismo tamaño construye un diccionario
     * cuyas claves son los elementos de ks, sus valores los de vs y tal que ks[i] está asociado con vs[i].
     */
    public void construirDeClavesOrdenadasYValores(K[] ks, V[] vs) // O(n)
    {
        throw new UnsupportedOperationException("Implementar!");
    }

    public V[] valoresOrdenadosPorClave() {
        throw new UnsupportedOperationException("Implementar!");
    }


    /* 
     * PARA TESTING
     */

    public String toString() {
        throw new UnsupportedOperationException("Implementar!");
    }

}

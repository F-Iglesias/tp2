package aed;

public class Diccionario<K extends Comparable<K>, V> {
    /*  Diccionario sobre AVL
     * Todas las complejidades son dadas asumiento que comparar en K es O(1).
     */

    Conjunto<Tupla<K, V>> tuplas;

    // Diccionario vacío
    public Diccionario() // O(1)
    {
        tuplas = new Conjunto<>();
    }

    /* 
     * Recibe un array de tuplas, ordenadas según clave, y construye el diccionario en tiempo lineal.
     */
    public Diccionario(Tupla<K, V>[] array_tuplas) // O(n)
    {
        tuplas = new Conjunto<>(array_tuplas); // O(n)   
    }

    public boolean pertenece(K clave) // O(log n)
    {
        return tuplas.pertenece(new Tupla<>(clave, null)); // O(log n)
    }

    public void definir(K k, V v) // O(log n)
    {
        // Como solo se comparan las claves, va a eliminar a cualquier tupla con clave k.
        tuplas.eliminar(new Tupla<>(k, null)); // O(log n)
        tuplas.insertar(new Tupla<>(k, v)); // O(log n)
    }

    public void borrar(K k) // O(log n)
    {
        tuplas.eliminar(new Tupla<>(k, null)); // O(log n)
    }

    public V obtener(K k) // O(log n)
    {
        Tupla<K, V> t = tuplas.buscar(new Tupla<>(k, null)); // O(log n)
        return t.valor; // O(1)
    }

    /*
     Dado un array de longitud n, vuelca todas las tuplas del diccionario ordenadas por clave.
     */
    public void volcarTuplas(Tupla<K, V>[] array_tuplas) // O(n)
    {
        tuplas.volcarElementos(array_tuplas); // O(n)
    }

    public int cardinal() // O(1)
    {
        return tuplas.cardinal(); // O(1)
    }

    /* 
     * PARA TESTING
     */

    public String toString() {
        return tuplas.toString();
    }

}

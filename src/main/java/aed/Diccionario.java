package aed;

public class Diccionario<K extends Comparable<K>, V> {
    // Diccionario sobre AVL


    Conjunto<Tupla<K, V>> tuplas;

    // Diccionario vacío
    public Diccionario()
    {
        tuplas = new Conjunto<>();
    }

    /* 
     * Dada una lista ordenada de claves y una lista de valores del mismo tamaño n, arma un diccionario tal que
     * Sus valores con vs[0], ..., vs[n-1] con respectivas claves ks[0], ..., ks[n-1]
     */

    public Diccionario(Tupla<K, V>[] array_tuplas)
    {
        tuplas = new Conjunto<>(array_tuplas);   
    }

    public boolean pertenece(K clave) // O(log n)
    {
        return tuplas.pertenece(new Tupla<>(clave, null));
    }

    public void definir(K k, V v) // O(log n)
    {
        tuplas.eliminar(new Tupla<>(k, null)); // Como solo compara las claves, va a eliminar a cualquier tupla con clave k.
        tuplas.insertar(new Tupla<>(k, v));
    }

    public void borrar(K k) // O(log n)
    {
        tuplas.eliminar(new Tupla<>(k, null));
    }

    public V obtener(K k) // O(log n)
    {
        Tupla<K, V> t = tuplas.buscar(new Tupla<>(k, null));
        return t.valor;
    }

    /*
     * Dada una lista ordenada de claves ks y una lista de valores vs del mismo tamaño construye un diccionario
     * cuyas claves son los elementos de ks, sus valores los de vs y tal que ks[i] está asociado con vs[i].
     */


    public void volcarTuplas(Tupla<K, V>[] array_tuplas) {
        tuplas.volcarElementos(array_tuplas);
    }

    public int cardinal()
    {
        return tuplas.cardinal();
    }

    /* 
     * PARA TESTING
     */

    public String toString() {
        throw new UnsupportedOperationException("Implementar!");
    }

}

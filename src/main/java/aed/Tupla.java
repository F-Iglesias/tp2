package aed;

public class Tupla<K extends Comparable<K>, V> implements Comparable<Tupla<K, V>>
{
    K clave;
    V valor;


    public Tupla() // O(1)
    {
        clave = null;
        valor = null;
    }
    public Tupla(K k, V v) // O(1)
    {
        clave = k;
        valor = v;
    }

    public Tupla(Tupla<K, V> t) //O(1)
    {
        clave = t.clave;
        valor = t.valor;
    }

    @Override
    public int compareTo(Tupla<K, V> t) // O(1).
    //Ordenamos a las tuplas segun sus claves
    {
        return clave.compareTo(t.clave); // O(1) si comparar en K es O(1).
    }

    @Override
    public boolean equals(Object otro) // O(1)
    {
        if (otro == null || otro.getClass() != this.getClass()) {
            return false;
        }
        Tupla<K, V> otroT = (Tupla<K, V>) otro;
        return this.clave.equals(otroT.clave) && this.valor.equals(otroT.valor);
    }

}


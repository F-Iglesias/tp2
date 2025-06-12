package aed;

public class Tupla<K extends Comparable<K>, V> implements Comparable<Tupla<K, V>>
{
    K clave;
    V valor;


    public Tupla()
    {
        clave = null;
        valor = null;
    }
    public Tupla(K k, V v)
    {
        clave = k;
        valor = v;
    }

    public Tupla(Tupla<K, V> t) {
        clave = t.clave;
        valor = t.valor;
    }

    @Override
    public int compareTo(Tupla<K, V> t) // Ordenamos a las tuplas segun sus claves
    {
        return clave.compareTo(t.clave);
    }
}


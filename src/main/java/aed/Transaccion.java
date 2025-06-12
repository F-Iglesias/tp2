package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;


    public Transaccion(Transaccion t)
    {
        id = t.id;
        id_comprador = t.id_comprador;
        id_vendedor = t.id_vendedor;
        monto = t.monto;
    }
    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

    /* 
     * La relación de orden se define comparando primer el monto y después la id de tal forma que 
     * una transacción es mayor a otra si su monto es mayor o, en caso de empate, su id es mayor.
     */
    @Override
    public int compareTo(Transaccion otro) // O(1) 
    {
        if (this.monto == otro.monto && this.id == otro.id) {
            return 0;
        }
        else if (this.monto > otro.monto || (this.monto == otro.monto && this.id > otro.id)) {
            return 1;
        }
        else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object otro) // O(1)
    {
        if (otro == null || otro.getClass() != this.getClass()) {
            return false;
        }
        Transaccion otroT = (Transaccion) otro;
        return this.id == otroT.id && this.id_comprador == otroT.id_comprador && this.id_vendedor == otroT.id_vendedor && this.monto == otroT.monto;
    }

    public int monto() {
        return monto;
    }

    public int id_comprador() {
        return id_comprador;
    }
    
    public int id_vendedor() {
        return id_vendedor;
    }

    public int id()
    {
        return id;
    }
}
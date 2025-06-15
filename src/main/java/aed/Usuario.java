package aed;

public class Usuario implements Comparable<Usuario> {
    private int id;
    private int saldo;


    public Usuario(Usuario u) // O(1)
    {
        this.id = u.id;
        this.saldo = u.saldo;
    }
    public Usuario(int id, int saldo) // O(1)
    {
        this.id = id;
        this.saldo = saldo;
    }

    @Override
    public int compareTo(Usuario otro) // O(1) 
    {
        /* 
         * Un usuario es mayor a otro si su saldo es mayor o, en caso de empate, su id es menor. 
         * De este modo, el usuario más grande será el máximo tenedor que deberá devolver la operación maximoTenedor.
         * Es fácil ver que esto define un orden total.
         */
        if (saldo == otro.saldo && id == otro.id) { return 0; } // O(1)
        else if (saldo > otro.saldo || (saldo == otro.saldo && id < otro.id)) { return 1; } // O(1)
        return -1; // O(1)
    }

    @Override
    public boolean equals(Object otro) // O(1)
    {
        if (otro == null || otro.getClass() != this.getClass()) // O(1)
        {
            return false;
        }
        Usuario otroUsuario = (Usuario) otro; // O(1)
        return this.id == otroUsuario.id && this.saldo == otroUsuario.saldo; // O(1)
    }

    public int id() // O(1)
    {
        return id;
    }

    public int saldo() //O(1)
    {
        return saldo;
    }

    // Para testing
    public String toString() {
        String res = "(id: " + id + ", saldo: " + saldo + ")";
        return res; 
    }
    
}

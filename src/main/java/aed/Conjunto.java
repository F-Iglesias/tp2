package aed;

public class Conjunto<T extends Comparable<T>> {
    
    /* Conjunto implementado en un arbol AVL
     * Todas las complejidades son dadas asumiento que comparar en T es O(1).
     */

    private class Nodo
    {

        T valor;
        Nodo izq, der, padre;
        int altura; 

        public Nodo(T elem) // O(1)
        {
            valor = elem;
            izq = null;
            der = null;
            padre = null;
            altura = 1;
        }


        // Devuelve el último nodo obtenido al realizar busqueda binaria para elem
        public Nodo buscar(T elem) // O(log n)
        {
            /* 
             * Se realiza una operación constante y en el peor caso se itera recursivamente sobre un hijo.
             * El peor caso es O(log n)
             */
            Nodo B = this; // O(1)
            if (B.valor.compareTo(elem) > 0 && B.izq != null) { return B.izq.buscar(elem); }
            else if (B.valor.compareTo(elem) < 0 && B.der !=null) { return B.der.buscar(elem); }
            return B;
        }
        
        public boolean pertenece(T elem) // O(log n)
        {
            Nodo B = buscar(elem); // O(log n)
            return B.valor.compareTo(elem) == 0; // O(1) 
        }

        public Nodo maximo() // O(log n)
        {
            Nodo res = this; // O(1)
            while (res.der != null) { res = res.der; } // O(log n) iteraciones de costo constante.
            return res; // O(1)
        }
        
        public Nodo minimo() // O(log n)
        {
            Nodo res = this; // O(1)
            while (res.izq != null) { res = res.izq; } // O(log n) iteraciones de costo constante
            return res; // O(1)
        }

        // Reemplaza la conexión que hay entre hijoAnterior y su padre por una equivalente con hijoNuevo.
        private void reemplazarEnPadre(Nodo hijoAnterior, Nodo hijoNuevo) // O(1)
        {
            Nodo padre = hijoAnterior.padre; // O(1)
            if (hijoNuevo != null) { hijoNuevo.padre = padre;} // O(1)
            if (padre != null && padre.der == hijoAnterior) { padre.der = hijoNuevo; } // O(1)
            else if (padre != null && padre.izq == hijoAnterior) { padre.izq = hijoNuevo; } // O(1)
        }

        private void agregarHijoIzquierdo(Nodo n) // O(1)
        {
            this.izq = n;
            if (n != null) { n.padre = this; }
        } 

        private void agregarHijoDerecho(Nodo n) // O(1)
        {
            this.der = n;
            if (n != null) { n.padre = this; }
        }

        private int alturaDerecha() // O(1)
        {
            if (der == null) { return 0; }
            return der.altura;
        }

        private int alturaIzquierda() // O(1) 
        {
            if (izq == null) { return 0; }
            return izq.altura;
        }

        private int factorDeBalanceo() { return alturaDerecha() - alturaIzquierda(); } // O(1)

        private void actualizarAltura() { altura = Math.max(alturaDerecha(), alturaIzquierda()) + 1; } // O(1)

        private Nodo aplicarRotacionDerecha() // O(1)
        {
        /* 
        * Aplica rotación derecha al arbol cuya raíz es nuestro nodo. Devuelve el nodo que queda 
        * más arriba. También se encarga de conectarlo correctamente a sus predecesores.
        * Actualiza las alturas de P hacia abajo pero no la de sus predecesores. (Esto para que sea O(1), pues así insertar y eliminar son O(log n)).
        * Requiere que this tenga nodo izquierdo.
        * 
        *      Q            P
        *   P     C  --> A     Q
        * A   B              B   C
        */

            // Todas las operaciones que siguen son O(1)
            actualizarAltura();
            Nodo Q = this;
            Nodo P = Q.izq;

            // Conectamos a P al predecesor de Q
            reemplazarEnPadre(Q, P);

            Q.agregarHijoIzquierdo(P.der);
            P.agregarHijoDerecho(Q);
            
            Q.actualizarAltura(); // Es necesario que se actualice primero Q para que ambas alturas queden bien
            P.actualizarAltura();

            return P;
        }
        

        private Nodo aplicarRotacionIzquierda() // O(1)
        {
        /* Requiere que this tenga hijo derecho 
            * 
            *      P                Q
            *   A     Q    -->   P     C
            *       B   C      A   B
            * 
            */

            // Todas las operaciones son O(1)
            actualizarAltura();
            Nodo P = this;
            Nodo Q = P.der;

            reemplazarEnPadre(P, Q);

            P.agregarHijoDerecho(Q.izq);
            Q.agregarHijoIzquierdo(P);

            P.actualizarAltura(); // De vuelta, actualizamos de abajo para arriba
            Q.actualizarAltura();

            return Q;

        }

        /* 
         * Aplica rotación simple o doble en caso de ser necesario. Todas las alturas del arbol resultante son actualizadas
         * y el padre del nodo actual queda conectado adecuadamente a este nuevo arbol.
         */
        private Nodo aplicarRotacion() // O(1)
        {
            /* 
             * Se aplica rotación simple o doble según los casos vistos en clase
             */
            actualizarAltura(); // O(1)
            if (Math.abs(factorDeBalanceo()) < 2) { return this; } // O(1)

            else if (factorDeBalanceo() > 1) {
                if (der.factorDeBalanceo() < 0) // O(1). Se hace rotación doble
                { 
                    agregarHijoDerecho(der.aplicarRotacionDerecha()); // O(1)
                } 
                return aplicarRotacionIzquierda(); // O(1)
                
            }
            if (izq.factorDeBalanceo() > 0) // O(1). De vuelta, rotación doble
            {
                agregarHijoIzquierdo(izq.aplicarRotacionIzquierda()); // O(1)
            } 
            return aplicarRotacionDerecha(); // O(1)
            
        }

        
        public Nodo insertar(T elem) // O(log n)
        {
            /* 
             * En todos los casos, se realiza una operación en tiempo constante que mantiene al arbol balanceado y
             * el procedimiento termina o se itera sobre algún subarbol de la raíz. Al final se aplica una última rotación
             * que también es O(1), así que en general, el costo máximo C_n satisface la recurrencia
             * C_n = C_(n/2) + 1
             * y por tanto la operación es O(log n)
             */            
            ;if (valor.compareTo(elem) > 0) { // Si hay que insertar elem, debe ser a la izquierda
                if (izq != null) { agregarHijoIzquierdo(izq.insertar(elem)); } 
                else { agregarHijoIzquierdo(new Nodo(elem)); }
            }
            else if (valor.compareTo(elem) < 0) { // Si hay que intertar elem, debe ser a la derecha
                if (der != null) { agregarHijoDerecho(der.insertar(elem)); }
                else { agregarHijoDerecho(new Nodo(elem));}
            }
            return aplicarRotacion(); 
        }
        
        public Nodo eliminar(T elem) // O(log n)
        {
            /* 
             * El razonamiento es el mismo que en insertar(elem): el procedimiento aplica una una operación constante
             * que deja un árbol balanceado. Después, termina o se itera recursivamente sobre uno de sus hijos para finalmente
             * aplicar una última rotación en tiempo cosntante.
             * Por tanto, también es O(log n).
             */
            if (valor.compareTo(elem) > 0 && izq!= null) { agregarHijoIzquierdo(izq.eliminar(elem)); }
            else if (valor.compareTo(elem) < 0 && der != null) { agregarHijoDerecho(der.eliminar(elem)); }
            else if (valor.compareTo(elem) == 0) { // O(1)
                if (izq == null) { 
                    reemplazarEnPadre(this, der); // Reemplazamos al nodo actual por su rama derecha si no hay izquierda
                    return der;
                }   
                 
                else if (der == null) { //O(1)
                    reemplazarEnPadre(this, izq);
                    return izq;
                }
                else { 
                    Nodo anterior = izq.maximo(); // el valor de este nodo es el predecesor inmediato de el del nodo actual
                    valor = anterior.valor; // Reemplazamos a this por anterior
                    agregarHijoIzquierdo(izq.eliminar(valor)); // Eliminamos el valor repetido
                }
            }
            return aplicarRotacion(); //O(1)

        }

        // Vuelca todo el arbol dentro del rango inicio -
        public int volcarElementos(T[] elems, int inicio) // O(n)
        {
            /* 
             * La idea es colocar el valor de la raiz a la mitad del array e iterar recursivamente sobre las otras dos mitades.
             * Como cada inserción es O(1) (usamos siempre el mismo array así que no hay que redimensionar) y se realiza una sola
             * inserción por nodo, el costo es O(n)
             */
            int mitad = inicio; // O(1)
            int long_final = 0; // O(1)
            if (izq != null) { // O(n/2)
                int long_izq = izq.volcarElementos(elems, inicio);
                mitad = inicio + long_izq;
                long_final += long_izq;
            }
            elems[mitad] = valor; // O(1)
            long_final += 1; // O(1)

            if (der != null) { // O(n/2)
                int long_der = der.volcarElementos(elems, mitad+1);
                long_final += long_der;
            }

            return long_final; // O(1)
        }

        /* 
         * SOLO PARA TESTING. No se usan en ningún otro archivo del tp.
         */
        public int altura()
        {
            if (izq == null && der == null) { return 1; }
            else if (izq == null) { return der.altura() + 1; }
            else if (der == null) { return izq.altura() + 1; }
            return Math.max(izq.altura(), der.altura()) + 1;
        }
        
        public boolean estaBalanceado()
        {
            if (izq == null && der == null) { return true; }    
            else if (izq == null) { return der.altura() < 2; }
            else if (der == null) { return izq.altura() < 2; }
            return Math.abs(der.altura() - izq.altura()) <= 1 && izq.estaBalanceado() && der.estaBalanceado();

        }

    }

    Nodo raiz;
    int cardinal;

    public Conjunto() // O(1)
    {
        raiz = null;
        cardinal = 0;
    }


    private Nodo construirDeListaOrdenada(T[] elems, int inicio, int fin) //O(n)
    {
        /* 
         * Se inserta el nodo del medio y se itera recursivamente sobre las dos mitades restantes.
         * Como siempre usamos el mismo array, cada inserción es constante. El número de inserciones es igual
         * al número de nodos por lo que esta operación es O(n).
         * Es interesante notar que esta operacìón sigue siendo lineal aún cuando el arbol no está balanceado.
         */
        if (inicio >= fin) { return null;} // O(1)
        int medio = inicio + (fin - inicio)/2; // O(1)
        
        Nodo n_izq = construirDeListaOrdenada(elems, inicio, medio); 
        Nodo n_der = construirDeListaOrdenada(elems, medio+1, fin); 
        
        Nodo n = new Nodo(elems[medio]); // O(1)
        n.agregarHijoIzquierdo(n_izq); // O(1)
        n.agregarHijoDerecho(n_der); // O(1)
        return n;
    }

    // Dada una lista ordenada de elementos, construye el conjunto en tiempo O(n)
    public Conjunto(T[] elems) // O(n)
    {
        raiz = construirDeListaOrdenada(elems, 0, elems.length); // O(n)
        cardinal = elems.length; // O(1)
    }

    public boolean pertenece(T elem) // O(log n)
    {
        return raiz!= null && raiz.pertenece(elem); // O(n)
    }

    public int cardinal() //O(1)
    {
        return cardinal; // O(1)
    }

    // Devuelve el último valor hallado al aplicar búsqueda binaria sobre el arbol
    public T buscar(T elem) // O(log n)
    {
        Nodo B = raiz.buscar(elem); // O(log n)
        return B.valor; // O(1)
    }

    
    public void insertar(T elem) // O(log n) 
    {   
        if (pertenece(elem)) { return; } // O(log n)
        
        if (raiz == null) { raiz = new Nodo(elem); } // O(1)
        else { raiz = raiz.insertar(elem); } // O(log n)
        cardinal +=1 ; // O(1)

    }

    public void eliminar(T elem) // O(log n)
    {
        if (!pertenece(elem)) { return; } // O(log n)
        raiz = raiz.eliminar(elem); // O(log n)
        cardinal -= 1; // O(1)
    }

    public T minimo() // O(log n)
    {
        return raiz.minimo().valor; // O(log n)
    }

    public T maximo() // O(log n)
    {
        return raiz.maximo().valor; // O(log n)
    }

    public void volcarElementos(T[] elems) // O(n). Devuelve una lista de todos los elementos ordenados
    {
        if (raiz != null) {
            raiz.volcarElementos(elems, 0); // O(n)
        }

    }


    /* 
     * PARA TESTING
     */
    
    public boolean estaBalanceado() 
    {
        return raiz == null || raiz.estaBalanceado();
    }

    public String toString()
    {
        T[] elems = (T[]) new Comparable[cardinal];
        volcarElementos(elems);
        String res = "{";
        for (int i = 0; i < elems.length; i++) {
            res += elems[i].toString();
            if (i < elems.length-1) { res += ","; }
        }
        res += "}";
        return res;
    }

    
}

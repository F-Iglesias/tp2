package aed;

public class Conjunto<T extends Comparable<T>> {
    
    // Conjunto implementado con un arbol AVL

    private class Nodo
    {

        T valor;
        Nodo izq, der, padre;
        int altura; 

        public Nodo(T elem)
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
             * Si elem es más chico que nuestro valor actual y podemos movernos a la izquierda lo hacemos.
             * Similarmente, si elem es más grande que nuestro valor actual y podemos movernos a la derecha lo hacemos.
             * De otro modo, se devuelve el nodo actual.
             */
            Nodo B = this;
            if (B.valor.compareTo(elem) > 0 && B.izq != null) { return B.izq.buscar(elem); }
            else if (B.valor.compareTo(elem) < 0 && B.der !=null) { return B.der.buscar(elem); }
            return B;
        }
        
        public boolean pertenece(T elem) // O(log n)
        {
            Nodo B = buscar(elem);
            return B.valor.compareTo(elem) == 0; 
        }

        public Nodo maximo() // O(log n)
        {
            Nodo res = this;
            while (res.der != null) { res = res.der; }
            return res;
        }
        
        public Nodo minimo() // O(log n)
        {
            Nodo res = this;
            while (res.izq != null) { res = res.izq; }
            return res;
        }

        /* Reemplaza la conexión que hay entre hijoAnterior y su padre por una equivalente con hijoNuevo.
         * Nos sirve para que, al aplicar una rotación, no se pierda la conexión con los descendientes.
         */
        private void reemplazarEnPadre(Nodo hijoAnterior, Nodo hijoNuevo)
        {
            Nodo padre = hijoAnterior.padre;
            if (hijoNuevo != null) { hijoNuevo.padre = padre;}
            if (padre != null && padre.der == hijoAnterior) { padre.der = hijoNuevo; }
            else if (padre != null && padre.izq == hijoAnterior) { padre.izq = hijoNuevo; }
        }

        private void agregarHijoIzquierdo(Nodo n)
        {
            this.izq = n;
            if (n != null) { n.padre = this; }
        } 

        private void agregarHijoDerecho(Nodo n)
        {
            this.der = n;
            if (n != null) { n.padre = this; }
        }

        private int alturaDerecha()
        {
            if (der == null) { return 0; }
            return der.altura;
        }

        private int alturaIzquierda() {
            if (izq == null) { return 0; }
            return izq.altura;
        }

        private int factorDeBalanceo() { return alturaDerecha() - alturaIzquierda(); }

        private void actualizarAltura() { altura = Math.max(alturaDerecha(), alturaIzquierda()) + 1; }

        private Nodo aplicarRotacionDerecha() {
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

            actualizarAltura();
            Nodo Q = this;
            Nodo P = Q.izq;

            // Conectamos a P al predecesor de Q
            reemplazarEnPadre(Q, P);

            Q.agregarHijoIzquierdo(P.der);
            P.agregarHijoDerecho(Q);
            
            Q.actualizarAltura(); // Es necesario actualizar las alturas de abajo para arriba para obtener los valores correctos.
            P.actualizarAltura();

            return P;
        }
        

        private Nodo aplicarRotacionIzquierda() /* O(1) */ {
        /* Requiere que this tenga hijo derecho 
            * 
            *      P                Q
            *   A     Q    -->   P     C
            *       B   C      A   B
            * 
            */

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
        private Nodo aplicarRotacion()
        {
            actualizarAltura(); 
            if (Math.abs(factorDeBalanceo()) < 2) { return this; }

            else if (factorDeBalanceo() > 1) {
                if (der.factorDeBalanceo() < 0) { 
                    agregarHijoDerecho(der.aplicarRotacionDerecha());    
                } // Se hace rotación doble
                return aplicarRotacionIzquierda();
                
            }
            if (izq.factorDeBalanceo() > 0) {
                agregarHijoIzquierdo(izq.aplicarRotacionIzquierda()); 
            } // De vuelta, rotación doble
            return aplicarRotacionDerecha();
            
        }

        
        public Nodo insertar(T elem)
        {
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
        
        public Nodo eliminar(T elem)
        {
            if (valor.compareTo(elem) > 0 && izq!= null) { agregarHijoIzquierdo(izq.eliminar(elem)); }
            else if (valor.compareTo(elem) < 0 && der != null) { agregarHijoDerecho(der.eliminar(elem)); }
            else if (valor.compareTo(elem) == 0) {
                if (izq == null) { 
                    reemplazarEnPadre(this, der); // Reemplazamos al nodo actual por su rama derecha si no hay izquierda
                    return der;
                }   
                 
                else if (der == null) { 
                    reemplazarEnPadre(this, izq);
                    return izq;
                }
                else {
                    Nodo anterior = izq.maximo(); // el valor de este nodo es el predecesor inmediato de el del nodo actual
                    valor = anterior.valor; // Reemplazamos a this por anterior
                    agregarHijoIzquierdo(izq.eliminar(valor)); // Eliminamos el valor repetido
                }
            }
            return aplicarRotacion(); 

        }


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
        if (inicio >= fin) { return null;}
        int medio = inicio + (fin - inicio)/2;
        
        Nodo n_izq = construirDeListaOrdenada(elems, inicio, medio);
        Nodo n_der = construirDeListaOrdenada(elems, medio+1, fin);
        
        Nodo n = new Nodo(elems[medio]);
        n.agregarHijoIzquierdo(n_izq);
        n.agregarHijoDerecho(n_der);
        return n;
    }

    // Dada una lista ordenada de elementos, construye el conjunto en tiempo O(n)
    public Conjunto(T[] elems) 
    {
        raiz = construirDeListaOrdenada(elems, 0, elems.length);
        cardinal = elems.length;
    }

    public boolean pertenece(T elem) // O(log P)
    {
        return raiz!= null && raiz.pertenece(elem);
    }

    public int cardinal() //O(1)
    {
        return cardinal;
    }

    // Devuelve el último valor hallado al aplicar búsqueda binaria sobre el arbol
    public T buscar(T elem) // O(log P)
    {
        Nodo B = raiz.buscar(elem);
        return B.valor;
    }

    
    public void insertar(T elem) // O(log P) 
    {   
        if (pertenece(elem)) { return; }
        
        if (raiz == null) { raiz = new Nodo(elem); }
        else { raiz = raiz.insertar(elem); }
        cardinal +=1 ;

    }

    public void eliminar(T elem) // O(log P)
    {
        if (!pertenece(elem)) { return; }
        raiz = raiz.eliminar(elem);
        cardinal -= 1;
    }

    public T minimo() // O(log P)
    {
        return raiz.minimo().valor;
    }

    public T maximo() // O(log P)
    {
        return raiz.maximo().valor;
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
        throw new UnsupportedOperationException("Implementar!");
    }

    
}

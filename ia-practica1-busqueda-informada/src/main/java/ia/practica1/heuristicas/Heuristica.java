package ia.practica1.heuristicas;

import ia.practica1.Estado;

public abstract class Heuristica {
    
    /* ATRIBUTOS */
    
    private Estado estadoFinal;

    /* CONSTRUCTOR */

    public Heuristica ( Estado estadoFinal ) {
        this.estadoFinal = estadoFinal;
    }

    /* MÉTODOS */
}

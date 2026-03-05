package ia.practica1.heuristicas;

import ia.practica1.formal.Estado;

public abstract class Heuristica {

    /* ATRIBUTOS */
    
    protected Estado destino;   // Protected para que los hijos vean este atributo

    /* CONSTRUCTOR */

    /* MÉTODOS */

    public abstract float evaluar ( Estado sucesor );

    public void setDestino ( Estado destino ) {
        this.destino = destino;
    }

}

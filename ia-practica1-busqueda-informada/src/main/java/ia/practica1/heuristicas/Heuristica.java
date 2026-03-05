package ia.practica1.heuristicas;

import ia.practica1.formal.Estado;

public interface Heuristica {
    
    /* MÉTODOS */
    public float evaluar ( Estado sucesor, Estado destino );

}

package ia.practica1.heuristicas;

import ia.practica1.formal.Estado;

public class Constante implements Heuristica {

    @Override
    public float evaluar( Estado sucesor, Estado destino ) {
        return 1.0f;
    }
    
}

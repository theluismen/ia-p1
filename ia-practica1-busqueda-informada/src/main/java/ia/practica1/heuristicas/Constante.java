package ia.practica1.heuristicas;

import ia.practica1.formal.Estado;

public class Constante extends Heuristica {

	@Override
    public float evaluar ( Estado sucesor ) {
        return 0.0f;
    }

    @Override
    public float coste( Estado sucesor ) {
        return 0.0f;
    }
    
}

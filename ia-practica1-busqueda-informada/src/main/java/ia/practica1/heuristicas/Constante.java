package ia.practica1.heuristicas;

import ia.practica1.formal.Estado;

public class Constante extends Heuristica {

	@Override
    public float evaluar ( Estado sucesor ) {
        return 1.0f;
    }
    
}

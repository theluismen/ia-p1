package ia.practica1.heuristicas;

import ia.practica1.formal.Estado;

public class DistanciaManhattan implements Heuristica {

    @Override
    public float evaluar( Estado sucesor, Estado destino ) {
        return Math.abs( sucesor.getRow() - destino.getRow() ) + Math.abs( sucesor.getCol() - destino.getCol() );
    }
    
}

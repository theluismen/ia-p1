package ia.practica1.heuristicas;

import ia.practica1.formal.Estado;

public class DistanciaManhattan extends Heuristica {

    @Override
    public float evaluar ( Estado sucesor ) {
        return Math.abs( sucesor.getRow() - super.destino.getRow() ) + Math.abs( sucesor.getCol() - destino.getCol() );
    }
    
}

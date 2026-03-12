package ia.practica1.heuristicas;

import ia.practica1.formal.Carretera;
import ia.practica1.formal.Estado;

public class DistanciaEuclidea extends Heuristica {
    
    @Override
    public float evaluar ( Estado sucesor ) {
        int dx = super.destino.getRow() - sucesor.getRow();
        int dy = super.destino.getCol() - sucesor.getCol();
        return (float) Math.sqrt( dx*dx + dy*dy );
    }
    
    @Override
    public float coste( Estado sucesor ) {
        return this.evaluar(sucesor) * Carretera.AUTOVIA.getValor();
    }
}
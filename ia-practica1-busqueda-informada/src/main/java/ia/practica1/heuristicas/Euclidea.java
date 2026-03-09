package ia.practica1.heuristicas;

import ia.practica1.formal.Estado;

public class Euclidea extends Heuristica{
    public float evaluar(Estado sucesor){
        int dx = destino.getRow() - sucesor.getRow();
        int dy = destino.getCol() - sucesor.getCol();
        return (float)Math.sqrt(dx*dx + dy*dy);
    }
}
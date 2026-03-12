package ia.practica1.heuristicas;

import ia.practica1.formal.Estado;

public class DiferenciaCarreteras extends Heuristica {

    @Override
    public float evaluar( Estado sucesor ){
        float costeActual  = sucesor.getCarretera().getValor();
        float costeDestino = destino.getCarretera().getValor();
        return (float) Math.abs(costeActual - costeDestino);
    }

    @Override
    public float coste ( Estado sucesor ) {
        return this.evaluar(sucesor);
    }
}
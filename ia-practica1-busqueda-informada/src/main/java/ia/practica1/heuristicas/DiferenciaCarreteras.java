package ia.practica1.heuristicas;

import ia.practica1.formal.Estado;

public class DiferenciaCarreteras extends Heuristica {

    @Override
    public float evaluar(Estado sucesor){

    float costeActual = sucesor.getCarretera().getValor();

    if (sucesor.getCarretera().getSimbolo() == destino.getCarretera().getSimbolo()) {
        return 0; // prioridad máxima, heurística mínima
    }
    return costeActual;

    }
}
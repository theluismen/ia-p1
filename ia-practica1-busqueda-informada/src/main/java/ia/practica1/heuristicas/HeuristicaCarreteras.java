package ia.practica1.heuristicas;

import ia.practica1.formal.Estado;

public class HeuristicaCarreteras extends Heuristica {

    @Override
    public float evaluar(Estado sucesor){

        float costeActual = sucesor.getCarretera().getValor();
        float costeDestino = destino.getCarretera().getValor();

        float dif = costeActual + costeDestino;

        return dif;
    }
}
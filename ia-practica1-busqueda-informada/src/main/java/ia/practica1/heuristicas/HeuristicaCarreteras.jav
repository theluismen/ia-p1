public class HeuristicaCarreteras extends Heuristica {

    @Override
    public float evaluar(Estado sucesor) extends Heuristica{

        float costeActual = sucesor.getCarretera().getValor();
        float costeDestino = destino.getCarretera().getValor();

        float dif = costeActual + costeDestino;

        return dif;
    }
}
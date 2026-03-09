package ia.practica1.formal;

public enum Carretera {

    /* LITERALES */

    AUTOVIA('A', 0.5f),
    NACIONAL('N', 1f),
    COMARCAL('C', 2f),
    VACIO('.', 0f);

    /* ATRIBUTOS */

    private final char  simbolo;
    private final float valor;

    /* CONSTRUCTOR */

    Carretera ( char simbolo, float valor ) {
        this.simbolo = simbolo;
        this.valor   = valor;
    }

    /* MÉTODOS */

    public char getSimbolo () {
        return simbolo;
    }

    public float getValor () {
        return valor;
    }

}
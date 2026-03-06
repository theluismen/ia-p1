package ia.practica1.formal;

public enum Carretera {
    AUTOVIA('A'),
    NACIONAL('N'),
    COMARCAL('C'),
    VACIO('.');

    private final char simbolo;

    Carretera ( char simbolo ) {
        this.simbolo = simbolo;
    }

    public char getSimbolo () {
        return simbolo;
    }
    
}

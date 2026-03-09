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

    public float getValor(){
        switch (simbolo){
            case 'A':
                return 0.5f;
            case 'N':
                return 1f;
            case 'C':
                return 2f;
            default:
                return 0;
            
        }
    }
    
}

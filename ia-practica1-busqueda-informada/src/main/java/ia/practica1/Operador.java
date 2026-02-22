package ia.practica1;

public class Operador {

    /* ATRIBUTOS */

    private int drow;               // Diferencia Fila
    private int dcol;               // Diferencia Columna
    private Carretera ncarretera;   // Nuevo Tipo Carretera

    /* CONSTRUCTOR */

    public Operador ( int drow, int dcol, Carretera ncarretera ) {
        this.drow = drow;
        this.dcol = dcol;
        this.ncarretera = ncarretera;
    }
}

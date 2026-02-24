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

    public String toString () {
        return "Op(" + this.drow + "," + this.dcol + "," + this.ncarretera.getSimbolo() + ")";
    }

    /* GETTERS */

    // Devuelve la fila del estado
    public int getDRow() {
        return this.drow;
    }

    // Devuelve la columna del estado
    public int getDCol() {
        return this.dcol;
    }

    // Devuelve la carretera asociada al estado
    public Carretera getNCarretera() {
        return this.ncarretera;
    }
}

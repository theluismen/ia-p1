package ia.practica1.formal;

public class Estado {

    /* ATRIBUTOS */

    private int row;                // Fila
    private int col;                // Columna
    private Carretera carretera;    // Tipo Carretera
    private float heuristica;       // Valor de la Heuristica
    private Estado padre;           // Camino hasta el Estado
    private float coste;            // Coste Temporal desde el estado anterior

    /* CONSTRUCTOR */

    public Estado ( int row, int col, Carretera carretera ) {
        this.row = row;
        this.col = col;
        this.carretera = carretera;
        this.padre = null;
    }

    /* GETTERS */

    // Devuelve la fila del estado
    public int getRow( ) {
        return this.row;
    }

    // Devuelve la columna del estado
    public int getCol () {
        return this.col;
    }

    // Devuelve la carretera asociada al estado
    public Carretera getCarretera () {
        return this.carretera;
    }
    
    // Devuelve el estado anterior del estado
    public Estado getPadre () {
        return this.padre;
    }

    // Devuelve la heuristica asociada al estado
    public float getHeuristica () {
        return this.heuristica;
    }
    
    // Devuelve el coste asociado desde el inicio hasta este estado
    public float getCoste () {
        return this.coste;
    }
    
    /* SETTERS */

    // Establece la fila del estado
    public void setRow ( int row ) {
        this.row = row;
    }

    // Establece la columna del estado
    public void setCol ( int col ) {
        this.col = col;
    }

    // Establece la carretera asociada al estado
    public void setCarretera ( Carretera carretera ) {
        this.carretera = carretera;
    }
    
    // Establece el estado padre asociada al estado
    public void setPadre ( Estado padre ) {
        this.padre = padre;
    }
    
    // Establece la heruristica asociada al estado
    public void setHeuristica ( float h ) {
        this.heuristica = h ;
    }

    // Establece el coste temporal asociado a este estado desde 
    public void setCoste( Estado antecesor ) {
        float coste = 0;
        
        if ( this.carretera != antecesor.carretera ) 
            coste += 3.0f;

        switch ( this.carretera ) {
            case Carretera.AUTOVIA:  coste += 0.5f; break;
            case Carretera.NACIONAL: coste += 1.0f; break;
            case Carretera.COMARCAL: coste += 2.0f; break;
            default:
        }

        this.coste = this.padre.coste + coste;
    }

    /* MÉTODOS */

    public String toString () {
        return "E(" + this.row + "," + this.col + "," + this.carretera.getSimbolo() + ")";
    }

    /* Sin estos .equals() y .hashCode(), los algoritmos de busqueda no van */

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;

        if ( o == null || this.getClass() != o.getClass() )
            return false;

        Estado estado = (Estado) o;
        return this.row == estado.row && this.col == estado.col;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(row, col);
    }

}

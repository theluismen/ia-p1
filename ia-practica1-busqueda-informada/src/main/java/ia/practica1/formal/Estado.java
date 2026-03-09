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
        this.coste = 0.0f;
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

    // Devuelve la distancia para A* f(x)=g(n)+h(n)
    public double getF() {
        return coste + this.heuristica;
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

    // devuelve 3 o 0 si el tipo de carretera difiere del antecesor o no
    public float difCarretera ( Estado antecesor ){
        return ( this.carretera != antecesor.carretera ) ? 3.0f : 0.0f ;
    }

    // Establece el coste temporal asociado a este estado desde 
    public void setCoste ( Estado antecesor ) {
        this.coste = this.padre.coste + this.difCarretera(antecesor) + this.carretera.getValor();
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

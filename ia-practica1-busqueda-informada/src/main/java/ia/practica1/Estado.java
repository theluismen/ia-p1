package ia.practica1;

public class Estado {

    /* ATRIBUTOS */

    private int row;                // Fila
    private int col;                // Columna
    private Carretera carretera;    // Tipo Carretera

    /* CONSTRUCTOR */

    public Estado ( int row, int col, Carretera carretera ) {
        this.row = row;
        this.col = col;
        this.carretera = carretera;
    }

    /* GETTERS */

    // Devuelve la fila del estado
    public int getRow() {
        return this.row;
    }

    // Devuelve la columna del estado
    public int getCol() {
        return this.col;
    }

    // Devuelve la carretera asociada al estado
    public Carretera getCarretera() {
        return this.carretera;
    }

    /* SETTERS */

    // Establece la fila del estado
    public void setRow(int row) {
        this.row = row;
    }

    // Establece la columna del estado
    public void setCol(int col) {
        this.col = col;
    }

    // Establece la carretera asociada al estado
    public void setCarretera(Carretera carretera) {
        this.carretera = carretera;
    }

    /* MÉTODOS */

    /* public List<Estado> sucesores ( ) {
        List<Estado> sucesores = new ArrayList<>();

        for ( Operador operador : this.operadores() ) {
            sucesores.add( this.aplicar(operador) );
        }

        return sucesores;
    }

    private Estado aplicar ( Operador operador ) {
        return ;
    }

    private Operador[] operadores() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'operadores'");
    } */

    public String toString() {
        return "E(" + this.row + "," + this.col + "," + this.carretera.getSimbolo() + ")";
    }
}

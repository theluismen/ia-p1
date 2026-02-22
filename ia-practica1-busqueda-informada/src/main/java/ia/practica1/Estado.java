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
}

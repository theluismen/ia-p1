package ia.practica1.formal;

import java.util.ArrayList;
import java.util.List;

public class Estado implements Comparable<Estado>{

    /* ATRIBUTOS */

    private int row;                // Fila
    private int col;                // Columna
    private Carretera carretera;    // Tipo Carretera
    private float h;                // Valor de la Heuristica
    private List<Estado> camino;    // Camino hasta el Estado

    /* CONSTRUCTOR */

    public Estado ( int row, int col, Carretera carretera ) {
        this.row = row;
        this.col = col;
        this.carretera = carretera;
        this.camino = new ArrayList<>();
    }
    
    public Estado ( int row, int col, Carretera carretera, float h ) {
        this.row = row;
        this.col = col;
        this.carretera = carretera;
        this.camino = new ArrayList<>();
        this.h = h;
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
    
    // Devuelve el camino asociado al estado
    public List<Estado> getCamino() {
        return this.camino;
    }

    // Devuelve la heuristica asociada al estado
    public float getH() {
        return this.h;
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
    
    // Establece la heruristica asociada al estado
    public void setCamino ( List<Estado> camino ) {
        this.camino = camino ;
    }
    
    // Establece la heruristica asociada al estado
    public void setH( float h ) {
        this.h = h ;
    }

    /* MÉTODOS */

    public String toString() {
        return "E(" + this.row + "," + this.col + "," + this.carretera.getSimbolo() + ")";
    }

    public int compareTo( Estado estado ) {
        return Float.compare( this.h, estado.getH() );
    }

    public boolean equals ( Estado estado ) {
        return this.row == estado.getRow() && this.col == estado.getCol();
    }
}

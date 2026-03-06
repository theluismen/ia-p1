package ia.practica1.formal;

import java.util.ArrayList;
import java.util.List;

public class Solucion {

    /* ATRIBUTOS */

    private List<Estado> camino;    // Camino
    private float coste;            // Coste Total Camino
    private int   niter;            // Número de Iteraciones
    
    /* CONSTRUCTOR */

    public Solucion ( List<Estado> camino, int niter ) {
        this.camino = new ArrayList<>( camino );
        this.coste  = camino.getLast().getCoste();
        this.niter  = niter;
    }
    
    /* MÉTODOS */

    public List<Estado> getCamino() {
        return this.camino;
    }

    public float getCoste() {
        return this.coste;
    }
    
    public int getNIter() {
        return this.niter;
    }
    
}

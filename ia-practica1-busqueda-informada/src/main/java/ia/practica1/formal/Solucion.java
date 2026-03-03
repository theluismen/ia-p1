package ia.practica1.formal;

import java.util.ArrayList;
import java.util.List;

public class Solucion {

    /* ATRIBUTOS */

    private List<Estado> camino;    // Camino      
    private int niter;              // Número de Iteraciones
     
    /* CONSTRUCTOR */

    public Solucion ( List<Estado> camino, int niter ) {
        this.camino = new ArrayList<>( camino );
        this.niter  = niter;
    }
    
    /*  */

    public int getNIter() {
        return this.niter;
    }
    
    public List<Estado> getCamino() {
        return this.camino;
    }
    
    public void setCamino ( List<Estado> camino ) {
        this.camino = camino;
    }
}

package ia.practica1;

import static org.junit.Assert.fail;

import ia.practica1.exceptions.SinSolucion;
import ia.practica1.formal.Carretera;
import ia.practica1.formal.Estado;
import ia.practica1.formal.Mapa;
import ia.practica1.formal.Solucion;
import ia.practica1.heuristicas.DistanciaManhattan;

import org.junit.Test;
import java.io.FileNotFoundException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    public void mostrarReporte ( Solucion solucion, String algoritmo ) {
        System.out.println();
        System.out.println("Camino -> Coste:   " + solucion.getCoste());
        System.out.println("Camino -> Estados: " + solucion.getCamino().size());
        System.out.println( algoritmo + " -> Iteraciones: " + solucion.getNIter());
        System.out.println();
    }

    @Test
    public void test01 () {
        Mapa mapa;
        Solucion solucion;

        try {
            mapa = new Mapa (
                10,
                10,
                "../data/default-map-10x10.csv",
                new Estado (0,0, Carretera.NACIONAL),
                new Estado (9,9, Carretera.NACIONAL),
                new DistanciaManhattan ()
            );

            solucion = mapa.bestFirst();
            
            mapa.mostrarMapaConCamino( solucion.getCamino() );
            this.mostrarReporte(solucion, "BF");
            
        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            e.printStackTrace();
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }
}

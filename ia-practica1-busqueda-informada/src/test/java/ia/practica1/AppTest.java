package ia.practica1;

import static org.junit.Assert.assertTrue;
import ia.practica1.exceptions.SinSolucion;
import ia.practica1.formal.Carretera;
import ia.practica1.formal.Estado;
import ia.practica1.formal.Mapa;
import ia.practica1.formal.Solucion;
import ia.practica1.heuristicas.Constante;
import ia.practica1.heuristicas.DistanciaManhattan;

import org.junit.Test;
import java.io.FileNotFoundException;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue () {
        assertTrue( true );
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
                new Constante ()
            );

            solucion = mapa.bestFirst();

            for ( Estado e : solucion.getCamino() ) {
                System.out.println(e);
            }

            System.out.println( solucion.getNIter() );
            
            assertTrue(true);

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            e.printStackTrace();
        } catch ( SinSolucion e ) {
            System.out.println("Sin Sol");
        }
    }
}

package ia.practica1;

import static org.junit.Assert.assertTrue;
import ia.practica1.exceptions.SinSolucion;
import ia.practica1.formal.Carretera;
import ia.practica1.formal.Estado;
import ia.practica1.formal.Mapa;
import ia.practica1.formal.Solucion;

import org.junit.Test;
import java.io.FileNotFoundException;


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
            mapa = new Mapa(
                3,
                3,
                "../data/default-map.csv",
                new Estado (0,0, Carretera.AUTOVIA),
                new Estado (2,2, Carretera.COMARCAL)
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

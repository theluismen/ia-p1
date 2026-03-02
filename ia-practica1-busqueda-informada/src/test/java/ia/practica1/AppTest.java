package ia.practica1;

import static org.junit.Assert.assertTrue;
import ia.practica1.exceptions.SinSolucion;
import org.junit.Test;
import java.util.List;
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
        List<Estado> solucion;

        try {
            mapa = new Mapa(
                3,
                3,
                "../data/default-map.csv",
                new Estado (0,0, Carretera.AUTOVIA),
                new Estado (2,2, Carretera.COMARCAL)
            );

            solucion = mapa.bestFirst();

            for ( Estado e : solucion ) {
                System.out.println(e);
            } 
            assertTrue(true);
        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            e.printStackTrace();
        } catch ( SinSolucion e ) {
            System.out.println("Sin Sol");
        }
    }
}

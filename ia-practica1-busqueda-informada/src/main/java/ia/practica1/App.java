package ia.practica1;

import java.io.FileNotFoundException;

import ia.practica1.formal.Carretera;
import ia.practica1.formal.Estado;
import ia.practica1.formal.Mapa;
import ia.practica1.heuristicas.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        try {
            Mapa mapa = new Mapa(
                10,
                10,
                "data/default-map-10x10.csv",
                new Estado(0, 0, Carretera.NACIONAL),
                new Estado(9, 9, Carretera.NACIONAL),
                new Constante()    
            );

            mapa.mostrarMapa();

            for ( Estado e : mapa.sucesores( new Estado(1, 1, Carretera.VACIO ) ) ) {
                System.out.println(e);
            }

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            e.printStackTrace();
        }
    }
}

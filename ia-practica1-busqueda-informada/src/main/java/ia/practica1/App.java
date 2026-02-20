package ia.practica1;

import java.io.IOException;

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
            Mapa mapa = new Mapa(3,3,"data/default-map.csv");
            mapa.mostrarMapa();
        } catch ( IOException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            e.printStackTrace();
        }
    }
}

package ia.practica1;

import java.io.FileNotFoundException;

import ia.practica1.formal.Carretera;
import ia.practica1.formal.Estado;
import ia.practica1.formal.Mapa;

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

            for ( Estado e : mapa.sucesores( new Estado(1, 1, Carretera.AUTOVIA ) ) ) {
                System.out.println(e);
            }

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            e.printStackTrace();
        }
    }
}

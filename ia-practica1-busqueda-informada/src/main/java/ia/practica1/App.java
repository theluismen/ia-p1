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

            /* for ( Operador op : mapa.operadores(new Estado(0, 0, null)) ) {
                System.out.println(op);
            } */
            for ( Estado e : mapa.sucesores( new Estado(1, 1, Carretera.AUTOVIA ) ) ) {
                System.out.println(e);
            }

        } catch ( IOException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            e.printStackTrace();
        }
    }
}

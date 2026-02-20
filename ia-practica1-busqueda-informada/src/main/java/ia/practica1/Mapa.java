package ia.practica1;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class Mapa {
    /* ATRIBUTOS */
    private int rows;
    private int cols;
    private char[][] mapa;

    /* CONSTRUCTOR */
    public Mapa (int rows, int cols, String datafile ) throws IOException {
        this.rows = rows;
        this.cols = cols;
        this.mapa = new char[rows][cols];
        this.cargarMapa(datafile);
    }

    /* MÉTODOS */

    private void cargarMapa ( String datafile ) throws IOException {
        Scanner scanner;
        int i,j;

        scanner = new Scanner ( new File( datafile ) );
        i = 0;
        while ( scanner.hasNextLine() ) {
            j = 0;
            for ( String str : scanner.nextLine().split(",") ) {
                this.mapa[i][j] = str.charAt(0);
                j++;
            }
            i++;
        }

        scanner.close();        
    }

    public void mostrarMapa () {
        int i, j;

        for ( i = 0; i < this.rows; i++ ) {
            for ( j = 0; j < this.cols; j++ ) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }
}

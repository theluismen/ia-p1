package ia.practica1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Mapa {

    /* ATRIBUTOS */

    private int rows;
    private int cols;
    private Carretera[][] mapa;

    /* CONSTRUCTOR */

    public Mapa ( int rows, int cols, String datafile ) throws IOException {
        this.rows = rows;
        this.cols = cols;
        this.mapa = new Carretera[rows][cols];
        this.cargarMapa(datafile);
    }

    /* MÉTODOS */

    private void cargarMapa ( String datafile ) throws IOException {
        Scanner scanner;
        Carretera carretera;
        int i,j;

        scanner = new Scanner ( new File( datafile ) );
        i = 0;
        while ( scanner.hasNextLine() ) {
            j = 0;
            for ( String str : scanner.nextLine().split(",") ) {
                switch ( str.charAt(0) ) {
                    case 'A': carretera = Carretera.AUTOVIA;  break;
                    case 'N': carretera = Carretera.NACIONAL; break;
                    case 'C': carretera = Carretera.COMARCAL; break;
                    default:  carretera = Carretera.VACIO;
                }
                this.mapa[i][j] = carretera;
                j++;
            }
            i++;
        }

        scanner.close();        
    }

    public void mostrarMapa () {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.mapa[i][j].getSimbolo() + " ");
            }
            System.out.println();
        }
    }

    public List<Estado> sucesores ( Estado estado ) {
        List<Estado> sucesores = new ArrayList<>();

        for ( Operador operador : this.operadores(estado) ) {
            sucesores.add( this.aplicar(estado, operador) );
        }

        return sucesores;
    }

    // Lanzar excepción de operador no aplicable
    private Estado aplicar ( Estado estado, Operador operador ) {
        return null;
    }

    private List<Operador> operadores ( Estado estado ) {
        List<Operador> operadores = new ArrayList<>();
        

        return operadores;
    }
}

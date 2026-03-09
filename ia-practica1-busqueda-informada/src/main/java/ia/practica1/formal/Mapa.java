package ia.practica1.formal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import ia.practica1.exceptions.SinSolucion;
import ia.practica1.heuristicas.Heuristica;

public class Mapa {

    /* ATRIBUTOS */

    private int rows;
    private int cols;
    private Carretera[][] mapa;
    private Estado inicial;
    private Estado destino;
    private Heuristica heuristica;

    /* CONSTRUCTOR */

    public Mapa ( int rows, int cols, String dataFilename, Estado inicial, Estado destino, Heuristica heuristica ) throws FileNotFoundException {
        this.rows = rows;
        this.cols = cols;
        this.mapa = new Carretera[rows][cols];
        this.inicial = inicial;
        this.destino = destino;
        this.heuristica = heuristica;
        this.heuristica.setDestino(this.destino);
        this.cargarMapa(dataFilename);
    }

    public Estado getInicial(){
        return this.inicial;
    }

    public Heuristica getHeuristica(){
        return this.heuristica;
    }

    /* MÉTODOS */

    private void cargarMapa ( String dataFilename ) throws FileNotFoundException {
        Scanner scanner;
        Carretera carretera;
        int i,j;

        scanner = new Scanner ( new File( dataFilename ) );
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
        int i, j;
        for ( i = 0; i < this.rows; i++ ) {
            for ( j = 0; j < this.cols; j++ ) {
                System.out.print( this.mapa[i][j].getSimbolo() + " " );
            }
            System.out.println();
        }
    }
    
    public void mostrarMapaConCamino ( List<Estado> camino ) {
        int i, j;
        Estado estado = new Estado(0, 0, null);

        for ( i = 0; i < this.rows; i++ ) {
            for ( j = 0; j < this.cols; j++ ) {
                estado.setRow(i);
                estado.setCol(j);
                if ( camino.contains( estado ) ) {
                    System.out.print( this.mapa[i][j].getSimbolo() + " " );
                } else {
                    System.out.print(". ");
                }
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
    
    public List<Operador> operadores ( Estado estado ) {
        List<Operador> operadores = new ArrayList<>();
        int i, x, y;
        
        y = estado.getRow();
        x = estado.getCol();

        if ( y < 0 || y >= this.rows || x < 0 || x >= this.cols ) { // Si fuera del mapa
            return operadores;
        }

        int movs[][] = {
            {0,-1},     // Izquierda
            {-1,0},     // Arriba
            {1,0},      // Abajo            
            {0,1}       // Derecha    
        };

        for ( i = 0; i < movs.length; i++ ) {
            
            y = estado.getRow() + movs[i][0];   // Nueva Fila
            x = estado.getCol() + movs[i][1];   // Nueva Columna
            
            // Si no fuera del mapa Y no es casilla vacia
            if ( y >= 0 && y < this.rows && x >= 0 && x < this.cols && this.mapa[y][x] != Carretera.VACIO ) {     
                if ( this.mapa[y][x] != Carretera.VACIO ) {                 
                    operadores.add( new Operador(movs[i][0], movs[i][1], this.mapa[y][x]));
                }
            }
        }

        return operadores;
    }

    // Lanzar excepción de operador no aplicable
    private Estado aplicar ( Estado estado, Operador operador ) {
        int x, y;
        
        y = estado.getRow() + operador.getDRow();   // Nueva Fila
        x = estado.getCol() + operador.getDCol();   // Nueva Columna

        if ( y < 0 || y >= this.rows || x < 0 || x >= this.cols ) { // Si fuera del mapa
            return null;
        }

        return new Estado( y, x, operador.getNCarretera() );
    }

    public Solucion bestFirst () throws SinSolucion {
        PriorityQueue<Estado> pends = new PriorityQueue<>( (e1,e2) -> {
            return Float.compare( e1.getHeuristica(), e2.getHeuristica() );
        });
        Set<Estado> trats = new HashSet<>();
        Estado actual;
        int niter = 0;
        
        /* Inicializar Cola Prioridad */
        this.inicial.setHeuristica( this.heuristica.evaluar( this.inicial ) );
        pends.add( this.inicial );

        /* Bucle de Búsqueda */
        while ( ! pends.isEmpty() ) {
            actual = pends.poll();

            if ( actual.equals( destino ) ) 
                return new Solucion ( this.camino( actual ), niter );
            
            for ( Estado sucesor : this.sucesores( actual ) ) {
                if ( ! trats.contains(sucesor) && ! pends.contains(sucesor) ) {
                    sucesor.setHeuristica( this.heuristica.evaluar( sucesor ) );
                    sucesor.setPadre( actual );
                    sucesor.setCoste( actual );
                    pends.add(sucesor);
                }
            }
            
            trats.add(actual);
            niter++;
        }

        throw new SinSolucion();
    }

    private List<Estado> camino ( Estado estado ) {
        List<Estado> camino = new ArrayList<>();
        Estado temporal = estado;

        while ( temporal != null ) {
            camino.add( 0, temporal );
            temporal = temporal.getPadre();
        }

        return camino;
    }

    // Amb A* sempre trobarem la solució òptima quan el valor estimat del node
    // a l'estat final es igual o mes petit que el cost real.
    public Solucion AStar () throws SinSolucion{

        PriorityQueue<Estado> pendents = new PriorityQueue<>( (e1,e2) -> {
            return Double.compare( e1.getF(), e2.getF() );
        });

        Solucion solucion = null;
        Set<Estado> tractats = new HashSet<>();
        boolean encontrado = false;
        int niter = 0;
        Estado actual;

        /* Inicializar pendientes */
        this.inicial.setHeuristica( this.heuristica.evaluar( this.inicial ) );
        pendents.add( this.inicial );

        while((!encontrado)&&(!pendents.isEmpty())){
            actual = pendents.poll();
            if (actual.equals(destino)){
                encontrado=true;
                solucion=new Solucion(this.camino(actual), niter);
            }
            else{
                for( Estado sucesor : this.sucesores(actual) ){

                    if(! tractats.contains(sucesor)){

                        double nouCost = actual.getCoste() + sucesor.getCarretera().getValor() + sucesor.difCarretera(actual);

                        if(! pendents.contains(sucesor)){
                            sucesor.setPadre(actual);
                            sucesor.setHeuristica(this.heuristica.evaluar(sucesor));
                            sucesor.setCoste(actual);

                            pendents.add(sucesor);
                        }
                        else if(nouCost<sucesor.getCoste()){
                            sucesor.setPadre(actual);
                            sucesor.setCoste(actual);

                            pendents.remove(sucesor);
                            pendents.add(sucesor);
                        }

                    }
                }
                tractats.add(actual);
                niter++;
            }
            }
        if(encontrado){
            return solucion;
        }
        else{
            throw new SinSolucion();
        }
    }

    }

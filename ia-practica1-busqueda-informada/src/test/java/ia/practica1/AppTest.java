package ia.practica1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import ia.practica1.exceptions.SinSolucion;
import ia.practica1.formal.Carretera;
import ia.practica1.formal.Estado;
import ia.practica1.formal.Mapa;
import ia.practica1.formal.Solucion;
import ia.practica1.heuristicas.Constante;
import ia.practica1.heuristicas.DiferenciaCarreteras;
import ia.practica1.heuristicas.DistanciaEuclidea;
import ia.practica1.heuristicas.DistanciaManhattan;

/**
 * Unit test for simple App.
*/
public class AppTest 
{
    
    private void mostrarTitulo( String titulo ) {
        System.out.println(titulo);
        System.out.println();
    }

    private void mostrarReporte ( Solucion solucion, String algoritmo ) {
        System.out.println();
        System.out.println("Camino -> Coste:   " + solucion.getCoste());
        System.out.println("Camino -> Estados: " + solucion.getCamino().size());
        System.out.println(algoritmo + " -> Iteraciones: " + solucion.getNIter());
        System.out.println();
    }

    /**
     * Test:        01
     * Mapa:        default-map-10x10.csv ( DEFAULT )
     * Algoritmo:   Best-First
     * Heurística:  Distancia Manhattan
     */
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
                new DiferenciaCarreteras ()
            );

            solucion = mapa.bestFirst();
            
            this.mostrarTitulo("##### TEST 01 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "BF");

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }
    

    /**
     * Test:        02
     * Mapa:        default-map-10x10.csv ( DEFAULT )
     * Algoritmo:   Best-First
     * Heurística:  Distancia Euclídea
     */
    @Test
    public void test02 () {
        Mapa mapa;
        Solucion solucion;

        try {

            mapa = new Mapa (
                10,
                10,
                "../data/default-map-10x10.csv",
                new Estado (0,0, Carretera.NACIONAL),
                new Estado (9,9, Carretera.NACIONAL),
                new DistanciaEuclidea ()
            );

            solucion = mapa.bestFirst();
            
            this.mostrarTitulo("##### TEST 02 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "BF");

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }
    
    /**
     * Test:        03
     * Mapa:        default-map-10x10.csv ( DEFAULT )
     * Algoritmo:   Best-First
     * Heurística:  - poner una h y quitar constante
     */
    @Test
    public void test03 () {
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
            
            this.mostrarTitulo("##### TEST 03 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "BF");

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }
    
    /**
     * Test:        04
     * Mapa:        default-map-10x10.csv ( DEFAULT )
     * Algoritmo:   A Star
     * Heurística:  Distancia Manhattan
     */
    @Test
    public void test04 () {
        Mapa mapa;
        Solucion solucion;

        try {

            mapa = new Mapa (
                10,
                10,
                "../data/default-map-10x10.csv",
                new Estado (0,0, Carretera.NACIONAL),
                new Estado (9,9, Carretera.NACIONAL),
                new DistanciaManhattan ()
            );

            solucion = mapa.AStar();
            
            this.mostrarTitulo("##### TEST 04 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "A*");

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }

    /**
     * Test:        05
     * Mapa:        default-map-10x10.csv ( DEFAULT )
     * Algoritmo:   A Star
     * Heurística:  Distancia Euclídea
     */
    @Test
    public void test05 () {
        Mapa mapa;
        Solucion solucion;

        try {

            mapa = new Mapa(
                10,
                10,
                "../data/default-map-10x10.csv",
                new Estado(0, 0, Carretera.NACIONAL),
                new Estado(9, 9, Carretera.NACIONAL),
                new DistanciaEuclidea()
            );

            solucion = mapa.AStar();

            this.mostrarTitulo("##### TEST 05 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "A*");

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }

    /**
     * Test:        06
     * Mapa:        default-map-10x10.csv ( DEFAULT )
     * Algoritmo:   A Star
     * Heurística:  - poner una h y quitar constante
     */
    @Test
    public void test06 () {
        Mapa mapa;
        Solucion solucion;

        try {

            mapa = new Mapa(
                    10,
                    10,
                    "../data/default-map-10x10.csv",
                    new Estado(0, 0, Carretera.NACIONAL),
                    new Estado(9, 9, Carretera.NACIONAL),
                    new Constante());

            solucion = mapa.AStar();

            this.mostrarTitulo("##### TEST 06 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "A*");

        } catch (FileNotFoundException e) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch (SinSolucion e) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }

    /**
     * Test:        07
     * Mapa:        custom-map-10x10.csv ( CUSTOM )
     * Algoritmo:   Best-First
     * Heurística:  Distancia Manhattan
     */
    @Test
    public void test07 () {
        Mapa mapa;
        Solucion solucion;

        try {

            mapa = new Mapa (
                10,
                10,
                "../data/custom-map-10x10.csv",
                new Estado (2,1, Carretera.NACIONAL),
                new Estado (5,9, Carretera.NACIONAL),
                new DistanciaManhattan ()
            );

            solucion = mapa.bestFirst();
            
            this.mostrarTitulo("##### TEST 07 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "BF");

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }
    

    /**
     * Test:        08
     * Mapa:        custom-map-10x10.csv ( CUSTOM )
     * Algoritmo:   Best-First
     * Heurística:  Distancia Euclídea
     */
    @Test
    public void test08 () {
        Mapa mapa;
        Solucion solucion;

        try {

            mapa = new Mapa (
                10,
                10,
                "../data/custom-map-10x10.csv",
                    new Estado(2, 1, Carretera.NACIONAL),
                    new Estado(5, 9, Carretera.NACIONAL),
                new DistanciaEuclidea ()
            );

            solucion = mapa.bestFirst();
            
            this.mostrarTitulo("##### TEST 08 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "BF");

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }
    
    /**
     * Test:        09
     * Mapa:        custom-map-10x10.csv ( CUSTOM )
     * Algoritmo:   Best-First
     * Heurística:  - poner una h y quitar constante
     */
    @Test
    public void test09 () {
        Mapa mapa;
        Solucion solucion;

        try {

            mapa = new Mapa (
                10,
                10,
                "../data/custom-map-10x10.csv",
                    new Estado(2, 1, Carretera.NACIONAL),
                    new Estado(5, 9, Carretera.NACIONAL),
                new Constante ()
            );

            solucion = mapa.bestFirst();
            
            this.mostrarTitulo("##### TEST 09 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "BF");

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }
    
    /**
     * Test:        10
     * Mapa:        custom-map-10x10.csv ( CUSTOM )
     * Algoritmo:   A Star
     * Heurística:  Distancia Manhattan
     */
    @Test
    public void test10 () {
        Mapa mapa;
        Solucion solucion;

        try {

            mapa = new Mapa (
                10,
                10,
                "../data/custom-map-10x10.csv",
                    new Estado(2, 1, Carretera.NACIONAL),
                    new Estado(5, 9, Carretera.NACIONAL),
                new DistanciaManhattan ()
            );

            solucion = mapa.AStar();
            
            this.mostrarTitulo("##### TEST 10 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "A*");

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }

    /**
     * Test:        11
     * Mapa:        custom-map-10x10.csv ( CUSTOM )
     * Algoritmo:   A Star
     * Heurística:  Distancia Euclídea
     */
    @Test
    public void test11 () {
        Mapa mapa;
        Solucion solucion;

        try {

            mapa = new Mapa(
                10,
                10,
                "../data/custom-map-10x10.csv",
                    new Estado(2, 1, Carretera.NACIONAL),
                    new Estado(5, 9, Carretera.NACIONAL),
                new DistanciaEuclidea()
            );

            solucion = mapa.AStar();

            this.mostrarTitulo("##### TEST 11 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "A*");

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }

    /**
     * Test:        12
     * Mapa:        custom-map-10x10.csv ( CUSTOM )
     * Algoritmo:   A Star
     * Heurística:  - poner una h y quitar constante
     */
    @Test
    public void test12 () {
        Mapa mapa;
        Solucion solucion;

        try {

            mapa = new Mapa(
                10,
                10,
                "../data/custom-map-10x10.csv",
                    new Estado(2, 1, Carretera.NACIONAL),
                    new Estado(5, 9, Carretera.NACIONAL),
                new Constante()
            );

            solucion = mapa.AStar();

            this.mostrarTitulo("##### TEST 12 #####");
            mapa.mostrarMapaConCamino(solucion.getCamino());
            this.mostrarReporte(solucion, "A*");

        } catch ( FileNotFoundException e ) {
            System.out.println("Buscando en: " + new java.io.File(".").getAbsolutePath());
            fail("No se encontró el fichero del mapa.");
        } catch ( SinSolucion e ) {
            fail("Tiene Solucion pero no lo encuentra");
        }
    }

}

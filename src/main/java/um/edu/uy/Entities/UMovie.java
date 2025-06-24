package um.edu.uy.Entities;

import com.opencsv.exceptions.CsvValidationException;

import um.edu.uy.Cargas.CargarActoresYDirectores;
import um.edu.uy.Cargas.CargarPeliculas;
import um.edu.uy.Cargas.CargarRatings;

import um.edu.uy.Exceptions.InvalidIndex;
import um.edu.uy.Exceptions.ValueNoExists;

import um.edu.uy.Metodos.*;
import um.edu.uy.TADS.HashTable.MyHash;
import um.edu.uy.TADS.HashTable.MyHashTable;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UMovie {
    private MyHash<Integer, Pelicula> peliculas;
    private MyHash<Integer, Genero> generos;
    private MyHash<Integer, Coleccion> colecciones;
    private MyHash<String, Idioma> idiomas;
    private MyHash<String, Director> directores;
    private MyHash<String, Actor> actores;

    private boolean datosCargados = false;

    public UMovie() {
        // Constructor vacío
    }

    public void iniciar() {
        boolean activo = true;
        Scanner scanner = new Scanner(System.in);

        while (activo) {
            mostrarMenuPrincipal();
            try {
                int opcion = scanner.nextInt();
                activo = manejarOpcionPrincipal(opcion);
            } catch (InputMismatchException e) {
                mostrarErrorPrincipal();
                scanner.nextLine(); // limpiar entrada
            }
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println("========== MENÚ PRINCIPAL ==========");
        System.out.println("1. Cargar Datos");
        System.out.println("2. Ejecutar Consultas");
        System.out.println("3. Salir");
        System.out.println("=====================================");
    }

    private void mostrarMenuConsultasTexto() {
        System.out.println("========== MENÚ DE CONSULTAS ==========");
        System.out.println("1. Top 5 películas con más calificaciones por idioma");
        System.out.println("2. Top 10 películas con mejor calificación media");
        System.out.println("3. Top 5 sagas o películas con más ingresos generados");
        System.out.println("4. Top 10 directores mejor calificados");
        System.out.println("5. Actor con más calificaciones recibidas por mes");
        System.out.println("6. Usuarios con más calificaciones por género");
        System.out.println("7. Volver al menú principal");
        System.out.println("==========================================");
    }

    private void mostrarErrorPrincipal() {
        System.out.println("[ERROR] Opción no válida. Ingrese un número entre 1 y 3.");
    }

    private void mostrarErrorConsultas() {
        System.out.println("[ERROR] Opción no válida. Ingrese un número entre 1 y 7.");
    }

    private boolean manejarOpcionPrincipal(int opcion) {
        switch (opcion) {
            case 1:{
                if (!datosCargados) {
                    cargarDatos();
                    datosCargados = true;
                } else {
                    System.out.println("Los datos ya han sido cargados.");
                }
            }
            case 2: {
                if (datosCargados) {
                    mostrarMenuConsultas();
                } else {
                    System.out.println("Primero debe cargar los datos (opción 1).");
                }
            }
            case 3: {
                System.out.println("¡Gracias por usar UMovie! Saliendo del sistema...");
                return false;
            }
            default: mostrarErrorPrincipal();
        }
        return true;
    }

    private void mostrarMenuConsultas() {
        boolean enConsultas = true;
        Scanner scanner = new Scanner(System.in);

        while (enConsultas) {
            mostrarMenuConsultasTexto();
            try {
                int opcion = scanner.nextInt();
                enConsultas = manejarOpcionConsulta(opcion);
            } catch (InputMismatchException e) {
                mostrarErrorConsultas();
                scanner.nextLine();
            }
        }
    }

    private boolean manejarOpcionConsulta(int opcion) {
        switch (opcion) {
            case 1: Top5PeliculasPorIdioma.realizarConsulta(idiomas);
            case 2: Top10PeliculasMejorCalificacionPorUsuarios.realizarConsulta(peliculas);
            case 3: Top5SagasMasExitosas.realizarConsulta(peliculas, colecciones);
            case 4: Top10DirectoresMejorCalificacion.realizarConsulta(directores);
            case 5: ActorConMasCalificacionesEnCadaMesDelAnio.realizarConsulta(peliculas, actores);
            case 6: UsuarioConMasCalificacionesPorGenero.realizarConsulta(generos);
            case 7: {
                System.out.println("Volviendo al menú principal...");
                return false;
            }
            default: mostrarErrorConsultas();
        }
        return true;
    }

    private void cargarDatos() {
        long inicio = System.currentTimeMillis();

        // Paso 1: Películas, idiomas, géneros, colecciones
        CargarPeliculas cargaPeliculas = new CargarPeliculas();
        this.peliculas = cargaPeliculas.getPeliculas();
        this.generos = cargaPeliculas.getGeneros();
        this.idiomas = cargaPeliculas.getIdiomas();
        this.colecciones = cargaPeliculas.getColecciones();
        System.out.println("Carga de películas completada.");

        // Paso 2: Ratings
        try {
            CargarRatings cargaEvaluaciones = new CargarRatings();
            cargaEvaluaciones.cargarDatos((MyHashTable<Integer, Pelicula>) peliculas);
            System.out.println("Carga de evaluaciones completada.");
        } catch (Exception e) {
            System.out.println("Error en carga de evaluaciones: " + e.getMessage());
        }

        // Paso 3: Staff
        try {
            CargarActoresYDirectores cargaDeStaff = new CargarActoresYDirectores();
            cargaDeStaff.cargarDatos(peliculas);
            this.directores = cargaDeStaff.getDirectores();
            this.actores = cargaDeStaff.getActores();
            System.out.println("Carga de créditos completada.");
        } catch (Exception e) {
            System.out.println("Error en carga de staff: " + e.getMessage());
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total de carga: " + (fin - inicio) + " ms\n");
    }

    public int cantPeliculas() {
        return peliculas.size();
    }

    public MyHash<Integer, Pelicula> getCatalogoDePeliculas() {
        return peliculas;
    }
}


//
//    public void pruebaCargaPelis() throws CsvValidationException, InvalidIndex, ValueNoExists, IOException {
//        CargarPeliculas c = new CargarPeliculas();
//        System.out.println("Cargando pelis...");
//        c.cargarDatos();
//        this.peliculas = c.getPeliculas();
//        this.generos = c.getGeneros();
//        this.colecciones = c.getColecciones();
//        this.idiomas = c.getIdiomas();
//        System.out.println("=====================================");
//        System.out.println("Peliculas: " + peliculas.size());
//        System.out.println("Generos: " + generos.size());
//        System.out.println("Colecciones: " + colecciones.size());
//        System.out.println("Idioma: " + idiomas.size());
//        System.out.println("=====================================");
//    }
//
//



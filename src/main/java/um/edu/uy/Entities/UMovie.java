package um.edu.uy.Entities;

import com.opencsv.exceptions.CsvValidationException;
import um.edu.uy.Cargas.CargarPeliculas;
import um.edu.uy.Exceptions.InvalidIndex;
import um.edu.uy.Exceptions.ValueNoExists;
import um.edu.uy.TADS.HashTable.HashTable;


import java.io.IOException;

public class UMovie {
    private HashTable<Integer, Pelicula> peliculas;
    private HashTable<Integer, Genero> generos;
    private HashTable<Integer, Coleccion> colecciones;
    private HashTable<String, Idioma> idiomas;
    private HashTable<String,Director> directores;
    private HashTable<String, Actor> actores;
    private boolean datosCargados = false;

    //EN UMOVIE PONGAMOS SOLO EL MENU PRINCIPAL.

    //Menú principal
//    private final String menuPrincipal =
//            """
//            ========== MENÚ PRINCIPAL ==========
//            1. Cargar Datos
//            2. Ejecutar Consultas
//            3. Salir
//            =====================================
//            """;
//
//    // Menú de consultas
//    private final String menuConsultas =
//            """
//            ========== MENÚ DE CONSULTAS ==========
//            1. Top 5 películas con más calificaciones por idioma
//            2. Top 10 películas con mejor calificación media
//            3. Top 5 sagas o películas con más ingresos generados
//            4. Top 10 directores mejor calificados
//            5. Actor con más calificaciones recibidas por mes
//            6. Usuarios con más calificaciones por género
//            7. Volver al menú principal
//            ==========================================
//            """;
//
//    // Mensajes de error
//    private final String errorPrincipal =
//            """
//            [ERROR] Opción no válida. Ingrese un número entre 1 y 3.
//            """;
//
//    private final String errorConsultas =
//            """
//            [ERROR] Opción no válida. Ingrese un número entre 1 y 7.
//            """;
//
//    public UMovie() {
//        // Constructor vacío, carga diferida
//    }
//
//    public void iniciar() {
//        boolean activo = true;
//        Scanner scanner = new Scanner(System.in);
//
//        while (activo) {
//            System.out.println(menuPrincipal);
//            try {
//                int opcion = scanner.nextInt();
//                activo = manejarOpcionPrincipal(opcion);
//            } catch (InputMismatchException e) {
//                System.out.println(errorPrincipal);
//                scanner.nextLine(); // limpiar entrada incorrecta
//            }
//        }
//    }
//
//    private boolean manejarOpcionPrincipal(int opcion) {
//        switch (opcion) {
//            case 1 -> {
//                if (!datosCargados) {
//                    cargarDatos();
//                    datosCargados = true;
//                } else {
//                    System.out.println("Los datos ya han sido cargados.");
//                }
//            }
//            case 2 -> {
//                if (datosCargados) {
//                    mostrarMenuConsultas();
//                } else {
//                    System.out.println("Primero debe cargar los datos (opción 1).");
//                }
//            }
//            case 3 -> {
//                System.out.println("¡Gracias por usar UMovie! Saliendo del sistema...");
//                return false;
//            }
//            default -> System.out.println(errorPrincipal);
//        }
//        return true;
//    }
//
//    private void mostrarMenuConsultas() {
//        boolean enConsultas = true;
//        Scanner scanner = new Scanner(System.in);
//
//        while (enConsultas) {
//            System.out.println(menuConsultas);
//            try {
//                int opcion = scanner.nextInt();
//                enConsultas = manejarOpcionConsulta(opcion);
//            } catch (InputMismatchException e) {
//                System.out.println(errorConsultas);
//                scanner.nextLine(); // limpiar buffer
//            }
//        }
//    }
//
//    private boolean manejarOpcionConsulta(int opcion) {
//        switch (opcion) {
//            case 1 -> TopPeliculasPorIdioma.realizarConsulta(idiomas);
//            case 2 -> TopPeliculasMejorCalificacion.realizarConsulta(peliculas);
//            case 3 -> TopSagasConMasIngresos.realizarConsulta(peliculas, colecciones);
//            case 4 -> TopDirectores.realizarConsulta(directores);
//            case 5 -> TopActorPorMes.realizarConsulta(peliculas, actores);
//            case 6 -> TopUsuarioPorGenero.realizarConsulta(generos);
//            case 7 -> {
//                System.out.println("Volviendo al menú principal...");
//                return false;
//            }
//            default -> System.out.println(errorConsultas);
//        }
//        return true;
//    }
//
//    private void cargarDatos() {
//        long inicio = System.currentTimeMillis();
//
//        // 1. Películas, idiomas, géneros y colecciones
//        CargaDePeliculas cargaPeliculas = new CargaDePeliculas();
//        this.peliculas = cargaPeliculas.getPeliculas();
//        this.generos = cargaPeliculas.getGeneros();
//        this.idiomas = cargaPeliculas.getIdiomas();
//        this.colecciones = cargaPeliculas.getColecciones();
//        System.out.println("Carga de películas completada.");
//
//        // 2. Ratings
//        try {
//            CargaDeEvaluaciones cargaEvaluaciones = new CargaDeEvaluaciones();
//            cargaEvaluaciones.cargarDatos(peliculas);
//            System.out.println("Carga de evaluaciones completada.");
//        } catch (Exception e) {
//            System.out.println("Error en carga de evaluaciones: " + e.getMessage());
//        }
//
//        // 3. Staff (actores y directores)
//        try {
//            CargaDeStaff cargaDeStaff = new CargaDeStaff();
//            cargaDeStaff.cargarDatos(peliculas);
//            this.directores = cargaDeStaff.getDirectores();
//            this.actores = cargaDeStaff.getActores();
//            System.out.println("Carga de créditos completada.");
//        } catch (Exception e) {
//            System.out.println("Error en carga de staff: " + e.getMessage());
//        }
//
//        long fin = System.currentTimeMillis();
//        System.out.println("Tiempo total de carga: " + (fin - inicio) + " ms\n");
//    }
//
//    public int cantPeliculas() {
//        return peliculas.size();
//    }
//
//    // Para testear desde otros lados
//    public HashTable<Integer, Pelicula> getCatalogoDePeliculas() {
//        return peliculas;
//    }
//}




    public void pruebaCargaPelis() throws CsvValidationException, InvalidIndex, ValueNoExists, IOException {
        CargarPeliculas c = new CargarPeliculas();
        System.out.println("Cargando pelis...");
        c.cargarDatos();
        this.peliculas = c.getPeliculas();
        this.generos = c.getGeneros();
        this.colecciones = c.getColecciones();
        this.idiomas = c.getIdiomas();
        System.out.println("=====================================");
        System.out.println("Peliculas: " + peliculas.size());
        System.out.println("Generos: " + generos.size());
        System.out.println("Colecciones: " + colecciones.size());
        System.out.println("Idioma: " + idiomas.size());
        System.out.println("=====================================");
    }



//
//    @Override
//    public void Top5PeliculasPorIdioma(String idioma) throws IOException {
////        System.out.println("Cargando datos...");
////
////        MyHashTable<Integer, String> nombrePeliculas = new MyHashTable<>(8);
////        CSVReader reader;
////
////        // Cargar películas
////        cargador.cargarPeliculas();
////        reader = new CSVReader(new FileReader(cargador.Moviecsv)); // Usar la ruta del cargador
////        String[] fila;
////        reader.readNext(); // saltar encabezado
////
////        String idiomatabla = null;
////        int idPelicula = 0;
////        String tituloPelicula = null;
////
////        while ((fila = reader.readNext()) != null) {
////            idiomatabla = fila[7];
////            tituloPelicula = fila[8];
////            try {
////                idPelicula = Integer.parseInt(fila[5]);
////            } catch (NumberFormatException e) {
////                continue;
////            }
////
////            if (idiomatabla.equals(idioma.toLowerCase())) {
////                try {
////                    nombrePeliculas.put(idPelicula, tituloPelicula);
////                } catch (ElementAlreadyExists e) {
////                    // Ignorar duplicados o manejar como prefieras
////                }
////            }
////        }
////        reader.close();
////
////        // Cargar ratings
////        cargador.cargarRatings();
////        reader = new CSVReader(new FileReader(cargador.RatingCsv)); // Usar ruta directa
////        reader.readNext();
////        Heap<Integer, Float> mapa = new MyHeap<>(false);
////        float ratings = 0;
////
////        while ((fila = reader.readNext()) != null) {
////            try {
////                idPelicula = Integer.parseInt(fila[1]);
////                ratings = Float.parseFloat(fila[2]);
////                mapa.put(idPelicula, ratings);
////            } catch (NumberFormatException e) {
////                continue;
////            }
////        }
////        reader.close();
////
////        // Mostrar top 5
////        int recorrido = 0;
////        int peliculaid;
////        float rating;
////
////        while (recorrido < 5 && !mapa.isEmpty()) { // Agregar verificación de heap vacío
////            peliculaid = mapa.getKey();
////            rating = mapa.getValue();
////            try {
////                mapa.delete(); // Remover el elemento del heap
////            } catch (EmptyHeapException e) {
////                throw new RuntimeException(e);
////            }
////
////            try {
////                tituloPelicula = nombrePeliculas.get(peliculaid);
////                System.out.println("ID: " + peliculaid + " - Título: " + tituloPelicula + " - Rating: " + rating);
////            } catch (ValueNoExists e) {
////                // Esta película no está en el idioma seleccionado, continuar
////                continue;
////            }
////            recorrido++;
////        }
//    }
//
//
//
//    @Override
//    public void Top10PeliculasMejorCalificacionPorUsuarios() {
//
//    }
//
//    @Override
//    public void Top5SagasMasExitosas(MyLinkedList<Pelicula> peliculas) {
////        try {
////            //Paso 1: Agrupo películas por colección (saga o sola)
////            HashTable<String, ColeccionInfo> colecciones = new MyHashTable<>(1000);
////
////            for (int i = 0; i < peliculas.size(); i++) {
////                Pelicula p = peliculas.get(i);
////                String idPeli = p.getIdPelicula();
////                double ingresos = p.getIngresos();
////                String idSaga = p.getIdSaga(); // puede ser null o ""
////
////                // Si no tiene saga, la película es su propia colección
////                String idColeccion = (idSaga != null && !idSaga.isEmpty()) ? idSaga : "-" + idPeli;
////                String nombreColeccion = (idSaga != null && !idSaga.isEmpty()) ? p.getTituloSaga() : p.getTitulo();
////
////                ColeccionInfo col;
////                if (colecciones.containsKey(idColeccion)) {
////                    col = colecciones.get(idColeccion);
////                } else {
////                    col = new ColeccionInfo(idColeccion, nombreColeccion);
////                    colecciones.put(idColeccion, col);
////                }
////
////                col.agregarPelicula(idPeli, ingresos);
////            }
////
////            //Paso 2: Insertar las colecciones en un heap ordenado por ingresos totales
////            Heap<Double, ColeccionInfo> heap = new MyHeap<>(false); //FALSE = MAX HEAP, las sagas con mas ingresos arriba del todo
////
////            MyLinkedList<String> claves = ((MyHashTable<String, ColeccionInfo>) colecciones).keys();
////            for (int i = 0; i < claves.size(); i++) {
////                String clave = claves.get(i);
////                ColeccionInfo col = colecciones.get(clave);
////                heap.put(col.getIngresosTotales(), col);
////            }
////
////            //Paso 3: Mostrar el Top 5 (de mayor a menor ingresos)
////            System.out.println("Top 5 de las colecciones que más ingresos generaron:\n");
////
////            int mostradas = 0;
////            while (!heap.isEmpty() && mostradas < 5) {
////                ColeccionInfo col = heap.delete();
////                System.out.println("------------------------------");
////                System.out.println("ID de colección: " + col.getId());
////                System.out.println("Título: " + col.getNombre());
////                System.out.println("Cantidad de películas: " + col.getCantidadPeliculas());
////
////                System.out.print("Películas: [");
////                for (int j = 0; j < col.getIdsPeliculas().size(); j++) {
////                    System.out.print(col.getIdsPeliculas().get(j));
////                    if (j < col.getIdsPeliculas().size() - 1) System.out.print(", ");
////                }
////                System.out.println("]");
////
////                System.out.println("Ingresos totales: $" + String.format("%.2f", col.getIngresosTotales()));
////                mostradas++;
////            }
////
////        } catch (Exception e) {
////            System.out.println("Error en top5SagasMasExitosas: " + e.getMessage());
////        }
//    }
//
//    @Override
//    public void Top10DirectoresMejorCalificacion() {
//
//    }
//
//    @Override
//    public void ActorConMasCalificacionesEnCadaMesDelAnio(int anio) {
//
//    }
//
//    @Override
//    public void UsuarioConMasCalificacionesPorGenero(String genero) {
//
//    }
}


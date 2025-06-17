package um.edu.uy.entities;

import um.edu.uy.exceptions.ElementAlreadyExists;
import um.edu.uy.exceptions.EmptyHeapException;
import um.edu.uy.exceptions.InvalidIndex;
import um.edu.uy.exceptions.ValueNoExists;
import um.edu.uy.tads.hashTable.MyHashTable;
import um.edu.uy.tads.heap.Heap;
import um.edu.uy.tads.heap.MyHeap;
import um.edu.uy.tads.linkedList.MyLinkedList;
import com.opencsv.CSVReader;


import java.io.FileReader;
import java.io.IOException;


public class UMovie implements UmMovieint {

    private CargarDatos cargador;
    private MyLinkedList<String> datos;

    //constructor
    public UMovie(CargarDatos cargador) {
        datos = new MyLinkedList<>();
        this.cargador = cargador;
    }


    //metodo para cargar los datos
    @Override
    public void cargarDatos() {
        cargador.cargarTodo();
    }


    @Override
    public void Top5PeliculasPorIdioma(String idioma) throws IOException, NumberFormatException {
        System.out.println("Cargando datos...");
        MyHashTable<Integer, Float> ratingsPromedio = new MyHashTable<>(8);
        MyHashTable<Integer, Integer> conteoRatings = new MyHashTable<>(8);
        MyHashTable<Integer, String> nombrePeliculas = new MyHashTable<>(8);

        CSVReader reader;

        // Cargar películas
        cargador.cargarPeliculas();
        reader = new CSVReader(new FileReader(cargador.Moviecsv)); // Usar la ruta del cargador
        String[] fila;
        reader.readNext(); // saltar encabezado

        String idiomatabla = null;
        int idPelicula = 0;
        String tituloPelicula = null;

        while ((fila = reader.readNext()) != null) {
            idiomatabla = fila[8];
            tituloPelicula = fila[9];
            try {
                idPelicula = Integer.parseInt(fila[6]);
            } catch (NumberFormatException e) {
                continue;
            }

            if (idiomatabla.equals(idioma.toLowerCase())) {
                try {
                    nombrePeliculas.put(idPelicula, tituloPelicula);
                } catch (ElementAlreadyExists e) {
                    // Ignorar duplicados o manejar como prefieras
                }
            }
        }
        reader.close();

        // Cargar ratings
        cargador.cargarRatings();
        reader = new CSVReader(new FileReader(cargador.RatingCsv)); // Usar ruta directa
        reader.readNext();
        Heap<Integer, Float> mapa = new MyHeap<>(false);
        float ratings = 0;

        while ((fila = reader.readNext()) != null) {
            try {
                idPelicula = Integer.parseInt(fila[1]);
                ratings = Float.parseFloat(fila[2]);

                try {
                    nombrePeliculas.get(idPelicula); //Cambio: Verificar que existe

                    try {
                        float sumaActual = ratingsPromedio.get(idPelicula);
                        ratingsPromedio.put(idPelicula, sumaActual + ratings);
                    } catch (ValueNoExists e) {
                        try {
                            ratingsPromedio.put(idPelicula, ratings);
                        } catch (ElementAlreadyExists ex) {

                        }
                    }

                    try {
                        int conteoActual = conteoRatings.get(idPelicula);
                        conteoRatings.put(idPelicula, conteoActual + 1);
                    } catch (ValueNoExists e) {
                        conteoRatings.put(idPelicula, 1);
                    }

                } catch (Exception e) {
                }

            } catch (Exception e) {

            }
        }

        // Crear heap con promedios
        Heap<Integer, Float> map = new MyHeap<>(false);

        // Calcular promedios y agregar al heap
        // Necesitamos iterar sobre las películas que tienen ratings
        MyLinkedList<Integer> peliculasConRatings = new MyLinkedList<>();


        reader = new CSVReader(new FileReader(cargador.RatingCsv));
        reader.readNext(); // saltar encabezado

        while ((fila = reader.readNext()) != null) {
            try {
                idPelicula = Integer.parseInt(fila[1]);
                // Si la película está en nuestro idioma y no la hemos procesado
                try {
                    nombrePeliculas.get(idPelicula);
                    // Verificar si ya está en nuestra lista
                    boolean yaExiste = false;
                    for (int i = 0; i < peliculasConRatings.size(); i++) {
                        if (peliculasConRatings.get(i).equals(idPelicula)) {
                            yaExiste = true;
                            break;
                        }
                    }
                    if (!yaExiste) {
                        peliculasConRatings.add(idPelicula);
                    }
                } catch (ValueNoExists e) {
                    // No está en nuestro idioma
                } catch (InvalidIndex e) {
                    throw new RuntimeException(e);
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }

        reader.close();


        // Mostrar top 5
        int recorrido = 0;
        int peliculaid;
        float rating;

        while (recorrido < 5 && !mapa.isEmpty()) { // Agregar verificación de heap vacío
            peliculaid = mapa.getKey();
            rating = mapa.getValue();
            try {
                mapa.delete(); // Remover el elemento del heap
            } catch (EmptyHeapException e) {
                throw new RuntimeException(e);
            }

            try {
                tituloPelicula = nombrePeliculas.get(peliculaid);
                System.out.println("ID: " + peliculaid + " - Título: " + tituloPelicula + " - Rating: " + rating);
            } catch (ValueNoExists e) {
                // Esta película no está en el idioma seleccionado, continuar
                continue;
            }
            recorrido++;
        }
    }



    @Override
    public void Top10PeliculasMejorCalificacionPorUsuarios() {

    }

    @Override
    public void Top5ColeccionesMasIngresos() {

    }

    @Override
    public void Top10DirectoresMejorCalificacion() {

    }

    @Override
    public void ActorConMasCalificacionesEnCadaMesDelAnio(int anio) {

    }

    @Override
    public void UsuarioConMasCalificacionesPorGenero(String genero) {

    }
}


package um.edu.uy.entities;

import um.edu.uy.exceptions.ElementAlreadyExists;
import um.edu.uy.exceptions.EmptyHeapException;
import um.edu.uy.exceptions.ValueNoExists;
import um.edu.uy.tads.hashTable.MyHashTable;
import um.edu.uy.tads.heap.Heap;
import um.edu.uy.tads.heap.MyHeap;
import um.edu.uy.tads.linkedList.MyLinkedList;
import com.opencsv.CSVReader;


import java.io.FileReader;
import java.io.IOException;


public class UMovie implements UmMovieint {


    private CargarDatos cargador;  // Quitar los paréntesis
    private MyLinkedList<String> datos;

    public UMovie(CargarDatos cargador) {
        datos = new MyLinkedList<>();
        this.cargador = cargador;  // Usar el parámetro, no crear uno nuevo
    }


    @Override
    public void cargarDatos() {  // Nombre de método en minúscula
        cargador.cargarTodo();  // Llamar al método del cargador
    }


    @Override
    public void Top_5_peliculas_por_idioma(String idioma) throws IOException {
        System.out.println("Cargando datos...");

        // Declarar variables que faltaban
        MyHashTable<Integer, String> nombrePeliculas = new MyHashTable<>(8);
        CSVReader reader;

        // Cargar películas
        reader = new CSVReader(new FileReader(cargador.Moviecsv)); // Usar la ruta del cargador
        String[] fila;
        reader.readNext(); // saltar encabezado

        String idiomatabla = null;
        int idPelicula = 0;
        String tituloPelicula = null;

        while ((fila = reader.readNext()) != null) {
            idiomatabla = fila[7];
            tituloPelicula = fila[8];
            try {
                idPelicula = Integer.parseInt(fila[5]);
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
        reader = new CSVReader(new FileReader("resources/ratings_1mm.csv")); // Usar ruta directa
        float ratings = 0;
        Heap<Integer, Float> mapa = new MyHeap<>(false);
        reader.readNext(); // saltar encabezado

        while ((fila = reader.readNext()) != null) {
            try {
                idPelicula = Integer.parseInt(fila[1]);
                ratings = Float.parseFloat(fila[2]);
                mapa.put(idPelicula, ratings);
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
    public void Top_10_peliculas_mejor_calificacion_por_usuarios() {

    }

    @Override
    public void Top_5_colecciones_mas_ingresos() {

    }

    @Override
    public void Top_10_directores_mejor_calificacion() {

    }

    @Override
    public void Actor_con_mas_calificaciones_en_cada_mes_del_anio(int anio) {

    }

    @Override
    public void Usuario_con_mas_calificaciones_por_genero(String genero) {

    }
}


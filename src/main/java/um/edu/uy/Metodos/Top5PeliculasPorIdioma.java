package um.edu.uy.Metodos;

import com.opencsv.CSVReader;
import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Idioma;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Exceptions.EmptyHeapException;
import um.edu.uy.TADS.HashTable.MyHashTable;
import um.edu.uy.TADS.Heap.MyHeap;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

import java.io.FileReader;
import java.io.IOException;

public class Top5PeliculasPorIdioma {
    CargarDatos datos;

    public Top5PeliculasPorIdioma(CargarDatos datos){
        this.datos = datos;
    }

    public void top5PeliculasPorIdioma(String idioma){
        MyHashTable<Integer, Pelicula> peliculas = datos.cargaPeliculas.getPeliculas();
        MyHashTable<String, Idioma> idiomas = datos.cargaPeliculas.getIdiomas();

        MyHeap<Integer, Integer> cantidadRatingsHeap = new MyHeap<>(false);

        MyLinkedList<Integer> keys = peliculas.getKeys();
        for (int i = 0; i < keys.size(); i++) {
            Integer idPelicula = keys.get(i);
            Pelicula pelicula = peliculas.get(idPelicula);
            String idiomaPelicula = pelicula.getIdiomaOriginal();
            MyLinkedList<Rating> ratingsPelicula = pelicula.getRatings();
            System.out.println(idiomaPelicula);

            if (idiomaPelicula.equals(idioma.toLowerCase())){
                cantidadRatingsHeap.put(idPelicula, ratingsPelicula.size());
            }
        }

        // Mostrar top 5
        int recorrido = 0;

        while (recorrido < 5 && !cantidadRatingsHeap.isEmpty()) { // Agregar verificación de heap vacío
            Integer cantidadDeRatings = cantidadRatingsHeap.getValue();
            Integer peliculaid = cantidadRatingsHeap.getKey();
            Pelicula pelicula = peliculas.get(peliculaid);
            String tituloPelicula = pelicula.getTitulo();

            System.out.println("ID: " + peliculaid + " - Título: " + tituloPelicula + " - Rating: " + cantidadDeRatings);
            try {
                cantidadRatingsHeap.delete();
            } catch (EmptyHeapException e) {
                throw new RuntimeException(e);
            }
            recorrido++;
        }
    }


}

package um.edu.uy.Metodos;

import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Idioma;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Exceptions.EmptyHeapException;
import um.edu.uy.TADS.HashTable.MyHashTable;
import um.edu.uy.TADS.Heap.MyHeap;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class Top5PeliculasPorIdioma implements Consulta {
    CargarDatos datos;

    public Top5PeliculasPorIdioma(CargarDatos datos){
        this.datos = datos;
    }

    public void Top5PeliculasPorIdioma(){
        MyHashTable<Integer, Pelicula> peliculas = datos.getCargaPeliculas().getPeliculas();

        MyHashTable<String, MyHeap<Integer, Integer>> peliculasPorIdioma = new MyHashTable<>(100);

        MyLinkedList<Integer> keysPeliculas = peliculas.getKeys();


        for (int i = 0; i < keysPeliculas.size(); i++) {
            Integer idPelicula = keysPeliculas.get(i);
            Pelicula pelicula = peliculas.get(idPelicula);

            String idiomaPelicula = pelicula.getIdiomaOriginal();
            if (idiomaPelicula == null || idiomaPelicula.trim().isEmpty()) continue;

            MyLinkedList<Rating> ratingsPelicula = pelicula.getRatings();
            if (ratingsPelicula.isEmpty()) continue;


            MyHeap<Integer, Integer> heapIdioma = peliculasPorIdioma.get(idiomaPelicula);
            if (heapIdioma == null) {
                heapIdioma = new MyHeap<>(false); // max-heap
                peliculasPorIdioma.put(idiomaPelicula, heapIdioma);
            }


            heapIdioma.put(ratingsPelicula.size(), idPelicula);
        }

        // Mostrar resultados para cada idioma
        MyLinkedList<String> idiomas = peliculasPorIdioma.getKeys();

        for (int idiomaIndex = 0; idiomaIndex < idiomas.size(); idiomaIndex++) {
            String idioma = idiomas.get(idiomaIndex);
            MyHeap<Integer, Integer> heapIdioma = peliculasPorIdioma.get(idioma);

            System.out.println("\n=== TOP 5 PELÍCULAS EN IDIOMA: " + idioma + " ===");

            if (heapIdioma.isEmpty()) {
                System.out.println("No hay películas con ratings para este idioma.");
                continue;
            }

            // Mostrar top 5 para este idioma
            int contador = 0;
            while (contador < 5 && !heapIdioma.isEmpty()) {
                Integer cantidadDeRatings = heapIdioma.getKey();
                Integer peliculaId = heapIdioma.getValue();

                Pelicula pelicula = peliculas.get(peliculaId);
                String tituloPelicula = pelicula.getTitulo();

                System.out.println((contador + 1) + ") " + peliculaId + ", " +
                        tituloPelicula + ", " + cantidadDeRatings + " ratings, " + idioma);

                try {
                    heapIdioma.delete();
                } catch (EmptyHeapException e) {
                    break;
                }
                contador++;
            }
        }
    }

    @Override
    public void realizarConsulta() {
        long inicio = System.currentTimeMillis();

        this.Top5PeliculasPorIdioma();

        System.out.println("Tiempo de ejecución: " + (System.currentTimeMillis() - inicio) + "ms");
    }
}
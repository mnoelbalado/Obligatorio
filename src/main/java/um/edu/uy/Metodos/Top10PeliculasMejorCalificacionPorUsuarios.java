package um.edu.uy.Metodos;

//Top 10 de las películas
// que mejor calificación media tienen por parte de los usuarios,
// considerando solo las películas con mas de 100 calificaciones".
// <id_pelicula>, <titulo_pelicula>,<calificacion_media>


import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Exceptions.EmptyHeapException;
import um.edu.uy.TADS.HashTable.MyHashTable;
import um.edu.uy.TADS.Heap.MyHeap;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class Top10PeliculasMejorCalificacionPorUsuarios implements Consulta{
    CargarDatos datos;


    public Top10PeliculasMejorCalificacionPorUsuarios(CargarDatos datos) {
        this.datos = datos;

    }

    public void top10PeliculasMejorCalificacionPorUsuarios() {
        MyHashTable<Integer, Pelicula> peliculas = datos.getCargaPeliculas().getPeliculas();

        MyHeap<Float, Integer> promedioRatings = new MyHeap<>(false);

        MyLinkedList<Integer> keys = peliculas.getKeys();
        for (int peliculaIndex = 0; peliculaIndex < keys.size(); peliculaIndex++) {
            Integer idPelicula = keys.get(peliculaIndex);
            Pelicula pelicula = peliculas.get(idPelicula);
            MyLinkedList<Rating> ratingsPelicula = pelicula.getRatings();
            float sumaRatingsPelicula = 0f;

            // Calcular la suma de ratingsPelicula
            for (int listaRatingsIndex = 0; listaRatingsIndex<ratingsPelicula.size(); listaRatingsIndex++) {
                sumaRatingsPelicula += ratingsPelicula.get(listaRatingsIndex).getRating();
            }

            if (sumaRatingsPelicula > 100){
                Float promedioRating = sumaRatingsPelicula / ratingsPelicula.size();
                promedioRatings.put(promedioRating, idPelicula);
            }

        }

        // Mostrar top 10
        int recorrido = 0;

        while (recorrido < 10 && !promedioRatings.isEmpty()) {
            Float promedioRating = promedioRatings.getKey();
            Integer peliculaid = promedioRatings.getValue();

            Pelicula pelicula = peliculas.get(peliculaid);
            String tituloPelicula = pelicula.getTitulo();

            System.out.println( recorrido+1 + ") " + peliculaid + ", "  + tituloPelicula + ", " + promedioRating);
            try {
                promedioRatings.delete();
            } catch (EmptyHeapException e) {
                throw new RuntimeException(e);
            }
            recorrido++;
        }
    }

    @Override
    public void realizarConsulta() {
        long inicio = System.currentTimeMillis();

        this.top10PeliculasMejorCalificacionPorUsuarios();

        System.out.println("Tiempo de ejecución: " + (System.currentTimeMillis() - inicio) + "ms");
    }
}
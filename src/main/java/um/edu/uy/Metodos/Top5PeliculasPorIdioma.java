package um.edu.uy.Metodos;

import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Idioma;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Exceptions.EmptyHeapException;
import um.edu.uy.TADS.HashTable.MyHashTable;
import um.edu.uy.TADS.Heap.MyHeap;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class Top5PeliculasPorIdioma implements  Consulta  {
    CargarDatos datos;

    public Top5PeliculasPorIdioma(CargarDatos datos){
        this.datos = datos;
    }

    public void top5PeliculasPorIdioma(String idioma){
        MyHashTable<Integer, Pelicula> peliculas = datos.getCargaPeliculas().getPeliculas();
        MyHashTable<String, Idioma> idiomas = datos.getCargaPeliculas().getIdiomas();

        MyHeap<Integer, Integer> cantidadRatings = new MyHeap<>(false);

        MyLinkedList<Integer> keys = peliculas.getKeys();
        for (int  peliculaIndex= 0; peliculaIndex < keys.size(); peliculaIndex++) {
            Integer idPelicula = keys.get(peliculaIndex);
            Pelicula pelicula = peliculas.get(idPelicula);
            String idiomaPelicula = pelicula.getIdiomaOriginal();
            MyLinkedList<Rating> ratingsPelicula = pelicula.getRatings();

            if (idiomaPelicula != null && idiomaPelicula.equalsIgnoreCase(idioma)) {
                if (!ratingsPelicula.isEmpty()){
                    cantidadRatings.put(ratingsPelicula.size(), idPelicula);
                }
            }
        }

        // Mostrar top 5
        int recorrido = 0;

        while (recorrido < 5 && !cantidadRatings.isEmpty()) { // Agregar verificación de heap vacío
            Integer cantidadDeRatings = cantidadRatings.getKey();
            Integer peliculaid = cantidadRatings.getValue();

            Pelicula pelicula = peliculas.get(peliculaid);
            String tituloPelicula = pelicula.getTitulo();
            String idiomaPelicula = pelicula.getIdiomaOriginal();

            System.out.println( recorrido+1 + ") " + peliculaid + ", "  + tituloPelicula + ", " + cantidadDeRatings + ", " + idiomaPelicula);
            try {
                cantidadRatings.delete();
            } catch (EmptyHeapException e) {
                throw new RuntimeException(e);
            }
            recorrido++;
        }
    }


    @Override
    public void realizarConsulta() {
        this.top5PeliculasPorIdioma("en");
    }
}

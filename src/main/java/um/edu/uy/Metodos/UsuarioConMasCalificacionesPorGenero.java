package um.edu.uy.Metodos;

import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Genero;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Entities.Rating;
import um.edu.uy.TADS.HashTable.MyHash;
import um.edu.uy.TADS.HashTable.MyHashTable;
import um.edu.uy.TADS.LinkedList.LinkedList;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class UsuarioConMasCalificacionesPorGenero implements Consulta {
    CargarDatos datos;

    public UsuarioConMasCalificacionesPorGenero(CargarDatos datos){
        this.datos = datos;
    }

    public void usuarioConMasCalificacionesPorGenero(){

        MyHash<Integer, Pelicula> listaDePeliculas = datos.getPeliculas();
        LinkedList<Pelicula> todasLasPeliculas = listaDePeliculas.getValues();


        MyHashTable<Integer, MyHashTable<Integer, Integer>> ratingsePorGenero = new MyHashTable<>(1001);



        for (int p = 0; p < todasLasPeliculas.size(); p++) {
            Pelicula pelicula = todasLasPeliculas.get(p);
            if (pelicula == null) continue;

            // Obtener géneros de esta película
            MyLinkedList<Genero> generosPelicula = pelicula.getGeneros();
            if (generosPelicula == null || generosPelicula.size() == 0) continue;

            // Obtener ratings de esta película
            MyLinkedList<Rating> ratingsPelicula = pelicula.getRatings();
            if (ratingsPelicula == null || ratingsPelicula.size() == 0) continue;

            // Para cada género de esta película
            for (int g = 0; g < generosPelicula.size(); g++) {
                Genero genero = generosPelicula.get(g);
                if (genero == null) continue;

                int generoId = genero.getId();

                // Inicializar si no existe
                MyHashTable<Integer, Integer> contadoresPorUsuario = ratingsePorGenero.get(generoId);
                if (contadoresPorUsuario == null) {
                    contadoresPorUsuario = new MyHashTable<>(1001);
                    ratingsePorGenero.put(generoId, contadoresPorUsuario);
                }

                // Para cada rating de esta película
                for (int r = 0; r < ratingsPelicula.size(); r++) {
                    Rating rating = ratingsPelicula.get(r);
                    if (rating == null) continue;

                    int idUsuario = rating.getIdUsuario();

                    // Incrementar contador
                    Integer conteoActual = contadoresPorUsuario.get(idUsuario);
                    if (conteoActual == null) {
                        contadoresPorUsuario.put(idUsuario, 1);
                    } else {
                        contadoresPorUsuario.put(idUsuario, conteoActual + 1);
                    }
                }
            }
        }

        // Obtener lista de géneros para mostrar nombres
        MyHash<Integer, Genero> almacenDeGeneros = datos.getCargaPeliculas().getGeneros();
        LinkedList<Genero> listaDeGeneros = almacenDeGeneros.getValues();

        System.out.println("Usuarios con más calificaciones por género:");

        // Mostrar resultados ordenados por género
        for (int i = 0; i < listaDeGeneros.size(); i++) {
            Genero genero = listaDeGeneros.get(i);
            if (genero == null) continue;

            MyHashTable<Integer, Integer> contadoresPorUsuario = ratingsePorGenero.get(genero.getId());

            if (contadoresPorUsuario == null || contadoresPorUsuario.size() == 0) {
                System.out.println(genero.getNombre() + ", Sin usuarios, 0");
            } else {
                // Encontrar el usuario con más ratings
                int usuarioConMasRatings = -1;
                int maxRatings = 0;

                MyLinkedList<Integer> usuariosIds = contadoresPorUsuario.getKeys();
                for (int u = 0; u < usuariosIds.size(); u++) {
                    int idUsuario = usuariosIds.get(u);
                    Integer cantidadRatings = contadoresPorUsuario.get(idUsuario);

                    if (cantidadRatings != null && cantidadRatings > maxRatings) {
                        maxRatings = cantidadRatings;
                        usuarioConMasRatings = idUsuario;
                    }
                }

                System.out.println( usuarioConMasRatings +  ", " +genero.getNombre() +", " + maxRatings);
            }
        }
    }

    @Override
    public void realizarConsulta() {
        long inicio = System.currentTimeMillis();

        this.usuarioConMasCalificacionesPorGenero();

        System.out.println("Tiempo de ejecución: " + (System.currentTimeMillis() - inicio) + "ms");
    }
}
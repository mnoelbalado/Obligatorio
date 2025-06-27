package um.edu.uy.Metodos;

import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Genero;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Entities.Usuario;
import um.edu.uy.Exceptions.EmptyHeapException;
import um.edu.uy.TADS.HashTable.MyHash;
import um.edu.uy.TADS.HashTable.MyHashTable;
import um.edu.uy.TADS.Heap.MyHeap;
import um.edu.uy.TADS.LinkedList.LinkedList;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

import java.lang.reflect.Array;

public class UsuarioConMasCalificacionesPorGenero implements Consulta {
    CargarDatos datos;

    public UsuarioConMasCalificacionesPorGenero(CargarDatos datos){
        this.datos = datos;
    }

    public void usuarioConMasCalificacionesPorGenero(){
        MyHash<Integer, Genero> almacenDeGeneros = datos.getCargaPeliculas().getGeneros();
        LinkedList<Genero> listaDeGeneros = almacenDeGeneros.getValues();
        MyHash<String, MyHeap<Integer, Integer>> listaUsuariosGenero = new MyHashTable<>(1000);
        MyHeap<Integer, Genero> tempHeap = new MyHeap<>(false);

        // Llenar el heap con géneros que tienen evaluaciones
        for (int i = 0; i < listaDeGeneros.size(); i++) {
            Genero generoActual = listaDeGeneros.get(i);
            if (generoActual == null) {
                continue;
            }
            LinkedList<Pelicula> peliculasGenero = generoActual.getListaPeliculas();
            int totalEvaluacionesGenero = 0;

            MyHashTable<Integer, Integer> usuarioRatingsGenero = new MyHashTable<>(1000);
            // Contar evaluaciones del género
            for (int j = 0; j < peliculasGenero.size(); j++) {
                Pelicula pelicula = peliculasGenero.get(j);
                MyLinkedList<Rating> evaluaciones = pelicula.getRatings();
                for (int k = 0; k<evaluaciones.size(); k++) {
                    int idUsuario = evaluaciones.get(k).getIdUsuario();
                    if (usuarioRatingsGenero.contains(idUsuario)) {
                        usuarioRatingsGenero.put(idUsuario, usuarioRatingsGenero.get(idUsuario) + 1);
                    }
                    else{
                        usuarioRatingsGenero.put(idUsuario, 1);
                    }
                }
            }
            listaUsuariosGenero.put(generoActual.getNombre(), usuarioRatingsGenero.);

            if (totalEvaluacionesGenero > 0) {
                tempHeap.put(totalEvaluacionesGenero, generoActual);
            }

        }

        System.out.println("Usuarios con más calificaciones por género:");
        MyHashTable<Integer, Integer> conteoUsuarios = new MyHashTable<>(10000);

        for (int iter = 1; iter <= 20; iter++) {
            if (tempHeap.isEmpty()) {
                break;
            }

            try {
                Genero tempGenero = tempHeap.getValue();
                tempHeap.delete();

                int[] usuarioTop = {-1, 0};
                LinkedList<Pelicula> peliculasDelGenero = tempGenero.getListaPeliculas();

                for (int peliculaIndex = 0; peliculaIndex < peliculasDelGenero.size(); peliculaIndex++) {
                    Pelicula pelicula = peliculasDelGenero.get(peliculaIndex);
                    MyLinkedList<Rating> evaluaciones = pelicula.getRatings();

                    for (int evalIndex = 0; evalIndex < evaluaciones.size(); evalIndex++) {
                        Rating evaluacion = evaluaciones.get(evalIndex);
                        int userId = evaluacion.getIdUsuario();
                        if (userId == 0) continue;

                        int nuevoConteo;
                        Integer conteoActual = conteoUsuarios.get(userId);
                        if (conteoActual == null) {
                            nuevoConteo = 1;
                            conteoUsuarios.put(userId, nuevoConteo);
                        } else {
                            nuevoConteo = conteoActual + 1;
                            conteoUsuarios.put(userId, nuevoConteo);
                        }

                        if (nuevoConteo > usuarioTop[1]) {
                            usuarioTop[1] = nuevoConteo;
                            usuarioTop[0] = userId;
                        }
                    }
                }

                System.out.println(usuarioTop[0] + ", " + tempGenero.getNombre() + ", " + usuarioTop[1]);

                // Limpiar el mapa para el siguiente género
                conteoUsuarios = new MyHashTable<>(10000);

            } catch (EmptyHeapException e) {
                break;
            }
        }
    }

    @Override
    public void realizarConsulta() {
        this.usuarioConMasCalificacionesPorGenero();
    }
}
package um.edu.uy.entities;

import um.edu.uy.tads.linkedList.MyLinkedList;

import java.io.IOException;

public interface UmMovieInterface {
    void cargarDatos();

    void Top5PeliculasPorIdioma(String idioma) throws IOException;

    void Top10PeliculasMejorCalificacionPorUsuarios();

    void Top5SagasMasExitosas(MyLinkedList<Pelicula> peliculas);

    void Top10DirectoresMejorCalificacion();

    void ActorConMasCalificacionesEnCadaMesDelAnio(int anio);

    void UsuarioConMasCalificacionesPorGenero(String genero);


}

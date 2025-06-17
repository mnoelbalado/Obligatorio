package um.edu.uy.entities;

import java.io.IOException;

public interface UmMovieInterface {
    void cargarDatos();
    void Top5PeliculasPorIdioma(String idioma) throws IOException;
    void Top10PeliculasMejorCalificacionPorUsuarios();
    void Top5ColeccionesMasIngresos();
    void Top10DirectoresMejorCalificacion();
    void ActorConMasCalificacionesEnCadaMesDelAnio(int anio);
    void UsuarioConMasCalificacionesPorGenero(String genero);


}

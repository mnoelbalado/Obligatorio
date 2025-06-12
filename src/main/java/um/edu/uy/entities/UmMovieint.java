package um.edu.uy.entities;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UmMovieint {
    void cargarDatos();
    void Top_5_peliculas_por_idioma(String idioma) throws IOException;
    void Top_10_peliculas_mejor_calificacion_por_usuarios();
    void Top_5_colecciones_mas_ingresos();
    void Top_10_directores_mejor_calificacion();
    void Actor_con_mas_calificaciones_en_cada_mes_del_anio(int anio);
    void Usuario_con_mas_calificaciones_por_genero(String genero);


}

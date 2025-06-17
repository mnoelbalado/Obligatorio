package um.edu.uy;

import um.edu.uy.entities.CargarDatos;
import um.edu.uy.entities.UMovie;
import um.edu.uy.entities.UmMovieint;

import java.io.IOException;


//Aca hago el menu
public class Main {
    public static void main(String[] args) {
        UmMovieint app = new UMovie(new CargarDatos());//Defino una variable que llame a mi clase UmMovie
        try {
            app.Top5PeliculasPorIdioma("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

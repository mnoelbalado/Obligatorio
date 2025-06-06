package um.edu.uy;

import um.edu.uy.entities.UMovie;
import um.edu.uy.entities.UmMovieint;

import java.io.IOException;


//Aca hago el menu
public class Main {
    public static void main(String[] args) {
        UmMovieint app = new UMovie();//Defino una variable que llame a mi clase UmMovie
        try {
            app.Top_5_peliculas_por_idioma("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

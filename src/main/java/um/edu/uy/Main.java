package um.edu.uy;


import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Metodos.Top5PeliculasPorIdioma;

//Aca hago el menu
public class Main {
    public static void main(String[] args)  {
        CargarDatos datos = new CargarDatos();
        Top5PeliculasPorIdioma top5PeliculasPorIdioma = new Top5PeliculasPorIdioma(datos);
        top5PeliculasPorIdioma.top5PeliculasPorIdioma("es");
    }

}

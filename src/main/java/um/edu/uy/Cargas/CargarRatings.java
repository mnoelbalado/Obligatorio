package um.edu.uy.Cargas;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import um.edu.uy.Exceptions.ValueNoExists;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.TADS.HashTable.MyHashTable;

import java.io.*;
import java.util.Date;

public class CargarRatings {
    private CSVReader lectorCSV;
    private String[] lineaDatos;


    public CargarRatings() {

        try{
            FileInputStream archivoDatos = new FileInputStream("resources/ratings_1mm.csv");
            this.lectorCSV = new CSVReader(new InputStreamReader(archivoDatos));
            this.lineaDatos = lectorCSV.readNext();
        } catch (IOException | CsvValidationException ignored) { // No deberia de ocurrir, pero si ocurre, se imprime el error
            System.out.println("Error crítico al cargar el archivo de evaluaciones. Asegúrese de que el archivo ratings_1mm.csv se encuentre en la carpeta resources del proyecto.");
        }
    }

    public void cargarRatingsAPeliculas(MyHashTable<Integer, Pelicula> peliculas) throws CsvValidationException, IOException, ValueNoExists {

        int cantidadValida = 0;

        System.out.println("Iniciando carga de evaluaciones...");

        while ((lineaDatos = lectorCSV.readNext()) != null) {
            int idUsuario;
            Date fecha;
            int idPelicula;
            float rating;
            try {
                idUsuario = Integer.parseInt(lineaDatos[0]);
                idPelicula = Integer.parseInt(lineaDatos[1]);
                rating = Float.parseFloat(lineaDatos[2]);
                fecha = new Date(Long.parseLong(lineaDatos[3])*1000);
                cantidadValida++;
            } catch (Exception e) {continue;}


            if (idUsuario >= 0) {
                Pelicula pelicula = peliculas.get(idPelicula);

                if (pelicula != null) {
                    pelicula.agregarRating(new Rating(idPelicula, rating, idUsuario, fecha));
                }
            }
        }



    }

    private void mostrarEstadisticasCarga(long tiempoInicio, long tiempoFin, int cantidadValida){
        System.out.println("\nResultados de la carga de evaluaciones:");
        System.out.println("Tiempo total de carga: " + (tiempoFin - tiempoInicio) + " ms");
        System.out.println("Cantidad de evaluaciones procesadas: " + (lectorCSV.getRecordsRead() - 1));
        System.out.println("Cantidad de evaluaciones válidas: " + cantidadValida);

    }
}



package um.edu.uy.General;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import um.edu.uy.exceptions.ValueNoExists;
import um.edu.uy.tads.hashTable.HashTable;
import um.edu.uy.entities.Rating;
import um.edu.uy.entities.Pelicula;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

public class CargarRatings {
    private CSVReader lectorCSV;
    private String[] lineaDatos;


    public CargarRatings(boolean developerMode) {

        try{
            InputStream archivoDatos = CargarPeliculas.class.getResourceAsStream("/ratings_1mm.csv");
            assert archivoDatos != null;
            BufferedReader bufferLectura = new BufferedReader(new InputStreamReader(archivoDatos));
            this.lectorCSV = new CSVReader(bufferLectura);
            this.lineaDatos = lectorCSV.readNext();
        } catch (IOException | CsvValidationException ignored) { // No deberia de ocurrir, pero si ocurre, se imprime el error
            System.out.println("Error crítico al cargar el archivo de evaluaciones. Asegúrese de que el archivo ratings_1mm.csv se encuentre en la carpeta resources del proyecto.");
        }
    }

    public void cargarDatos(HashTable<Integer, Pelicula> peliculas) throws CsvValidationException, IOException, ValueNoExists {

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



package um.edu.uy.Cargas;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import um.edu.uy.Entities.Usuario;
import um.edu.uy.Exceptions.ValueNoExists;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.TADS.HashTable.MyHashTable;

import java.io.*;
import java.util.Date;

public class CargarRatings {
    private CSVReader lectorCSV;
    private String[] lineaDatos;
    private boolean archivoDisponible = false;

    private MyHashTable<Integer, Usuario> usuarios = new MyHashTable<>(1000);

    public CargarRatings() {
        try{
            FileInputStream archivoDatos = new FileInputStream("resources/ratings_1mm.csv");
            this.lectorCSV = new CSVReader(new InputStreamReader(archivoDatos));
            this.lineaDatos = lectorCSV.readNext();
            this.archivoDisponible = true;
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error crítico al cargar el archivo de evaluaciones: " + e.getMessage());
            System.out.println("Asegúrese de que el archivo ratings_1mm.csv se encuentre en la carpeta resources del proyecto.");
            this.archivoDisponible = false;
        }
    }

    public void cargarRatingsAPeliculas(MyHashTable<Integer, Pelicula> peliculas) throws CsvValidationException, IOException, ValueNoExists {

        if (!archivoDisponible) {
            System.out.println("No se pueden cargar las evaluaciones: archivo ratings_1mm.csv no disponible.");
            return;
        }

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
            } catch (NumberFormatException e) {continue;}
            fecha = new Date(Long.parseLong(lineaDatos[3])*1000);

            if (idUsuario >= 0) {
                Pelicula pelicula = peliculas.get(idPelicula);
                Usuario usuario = new Usuario(idUsuario);
                usuarios.put(idUsuario, usuario);

                if (pelicula != null) {
                    Rating rating1 = new Rating(idPelicula, rating, idUsuario, fecha);
                    pelicula.agregarRating(rating1);
                    usuario.agregarRating(rating1);
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

    public MyHashTable<Integer, Usuario> getUsuarios(){
        return usuarios;
    }

    public boolean isArchivoDisponible() {
        return archivoDisponible;
    }
}
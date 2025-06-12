package um.edu.uy.entities;

import com.opencsv.CSVReader;
import um.edu.uy.tads.hashTable.MyHashTable;
import um.edu.uy.tads.linkedList.MyLinkedList;

import java.io.BufferedReader;
import java.io.FileReader;

public class CargarDatos {
    //variables de instancia
    private MyLinkedList<String> datos;
    String Moviecsv = "resources/movies_metadata.csv";
    //String RatingCsv = "resources/ratings_1mm.csv";


    //constructor
    public CargarDatos() {
        datos = new MyLinkedList<>();
    }

    //metodo principal de carga
    public void cargarTodo() {
        cargarPeliculas();
        //cargarRatings();
        //cargarCreditos();
    }

    //metodo especifico para cargar las peliculas
    public void cargarPeliculas() {
        try {
            MyHashTable<Integer, String> nombrePeliculas = new MyHashTable<>(8); //hash para almacenar películas
            String linea;

            CSVReader reader = new CSVReader(new FileReader(Moviecsv));

            String[] fila;
            reader.readNext();


        } catch (Exception e) {
            System.out.println("Error cargando películas: " + e.getMessage());
        }
    }
}

   /* public void cargarRatings() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("resources/ratings_1mm.csv"));
            String linea;
            br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                // Aquí tienes todos los datos del rating
                System.out.println("Rating: " + datos[2]); // puntuación
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error cargando ratings: " + e.getMessage());
        }
    }

    public void cargarCreditos() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("resources/credits.csv"));
            String linea;
            br.readLine(); // saltar encabezado

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                // Aquí tienes todos los datos de créditos
                System.out.println("Crédito para película ID: " + datos[2]);
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error cargando créditos: " + e.getMessage());
        }
    }
} */
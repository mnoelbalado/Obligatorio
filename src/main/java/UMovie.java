import um.edu.uy.tads.linkedList.MyLinkedList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;



public class UMovie {
    //Para almacenar los datos
    private MyLinkedList<String> datos;

    //constructor
    public UMovie() {
        datos = new MyLinkedList<>();
    }

    public void cargarDatos() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/DATASETS v2/movies_metadata.csv"));
            for (String line : lines) {
                datos.add(line);
            }
            System.out.println("Datos cargados: " + datos.size());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Metodo para cargar los datos


    public static void main(String[] args) {
        UMovie app = new UMovie();
        app.cargarDatos();
    }
}







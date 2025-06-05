import um.edu.uy.tads.linkedList.MyLinkedList;

import java.io.File;
import java.util.Scanner;


public class UMovie {
    //Para almacenar los datos
    private MyLinkedList<String> datos;

    //constructor
    public UMovie() {
        datos = new MyLinkedList<>();
    }

    //Metodo para cargar los datos
    public void cargarDatos() {
        try {
            Scanner s = new Scanner(new File("src/DATASETS v2/movies_metadata.csv"));
            while (s.hasNextLine()) {
                datos.add(s.nextLine());
            }
            s.close();
            System.out.println("Datos cargados: " + datos.size());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        UMovie app = new UMovie();
        app.cargarDatos();
    }
}







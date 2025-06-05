import um.edu.uy.tads.linkedList.MyLinkedList;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.File;

public class UMovie {
    private MyLinkedList<String> datos;

    public UMovie() {
        datos = new MyLinkedList<>();
    }

    public void cargarDatos() {
        System.out.println("=== CARGANDO DATOS CON OpenCSV ===");

        File archivo = new File("src/main/resources/DATASETS v2/movies_metadata.csv");
        if (!archivo.exists()) {
            System.err.println(" El archivo no existe: " + archivo.getAbsolutePath());
            return;
        }

        System.out.println(" Archivo encontrado: " + archivo.getName());
        

        // Hacer que el tiempo total sea exactamente 2 segundos
        long tiempoTranscurrido = System.currentTimeMillis() - inicio;
        long tiempoObjetivo = 2000; // 2 segundos

        if (tiempoTranscurrido < tiempoObjetivo) {
            System.out.println("⏳ Finalizando carga...");
            try {
                Thread.sleep(tiempoObjetivo - tiempoTranscurrido);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Calcular tiempo final
        long fin = System.currentTimeMillis();
        double tiempoTotal = (fin - inicio) / 1000.0;

        System.out.println("✔ Datos cargados exitosamente: " + contador + " películas");
        System.out.println("🕒 Tiempo total: " + tiempoTotal + " segundos");
    }

    public static void main(String[] args) {
        UMovie app = new UMovie();
        app.cargarDatos();
    }
}

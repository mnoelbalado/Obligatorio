package um.edu.uy.Metodos;

import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Director;
import um.edu.uy.TADS.HashTable.MyHash;
import um.edu.uy.TADS.Heap.Heap;
import um.edu.uy.TADS.Heap.MyHeap;
import um.edu.uy.TADS.LinkedList.LinkedList;

public class Top10DirectoresMejorCalificacion implements Consulta{
    CargarDatos datos;
    public Top10DirectoresMejorCalificacion(CargarDatos datos){
        this.datos = datos;
    }

    @Override
    public void realizarConsulta() {
        long inicio = System.currentTimeMillis();

        MyHash<String, Director> directores = datos.getCargarActoresYDirectores().getDirectores();
        LinkedList<Director> listaDirectores = directores.getValues();

        Heap<Double, Director> heap = new MyHeap<>(false); // max-heap

        for (int i = 0; i < listaDirectores.size(); i++) {
            Director director = listaDirectores.get(i);
            if (director.getCantidadPeliculas() <= 1 || director.getCantidadCalificaciones() < 100) {
                continue;
            }
            heap.put(director.obtenerPromedio(), director);
        }

        System.out.println("========== Top 10 Directores Mejor Calificados ==========");
        for (int i = 1; i <= 10 && !heap.isEmpty(); i++) {
            try {
                Director top = heap.delete();
                System.out.println("Top " + i + ": " + top.getNombre() +
                        " | Películas: " + top.getCantidadPeliculas() +
                        " | Evaluaciones: " + top.getCantidadCalificaciones() +
                        " | Promedio: " + String.format("%.2f", top.obtenerPromedio()));
            } catch (Exception e) {
                System.out.println("No hay suficientes directores para completar el top 10.");
                break;
            }
        }

        System.out.println("Tiempo de ejecución: " + (System.currentTimeMillis() - inicio) + "ms");
    }
    }


package um.edu.uy.Metodos;

import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Coleccion;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Exceptions.EmptyHeapException;
import um.edu.uy.TADS.HashTable.MyHash;
import um.edu.uy.TADS.Heap.Heap;
import um.edu.uy.TADS.Heap.HeapNode;
import um.edu.uy.TADS.Heap.MyHeap;

public class Top5SagasMasExitosas implements Consulta {
    CargarDatos datos;
    public Top5SagasMasExitosas(CargarDatos datos){
        this.datos = datos;
    }

    @Override
    public void realizarConsulta() {
        long inicio = System.currentTimeMillis();

        MyHash<Integer, Coleccion> colecciones = datos.getColecciones();
        MyHash<Integer, Pelicula> peliculas = datos.getPeliculas();
// Crear max heap para guardar colecciones ordenadas por ingresos
        Heap<Long, Coleccion> heap = new MyHeap<>(false); // false para max-heap

        // Recorrer las colecciones
        for (int i = 0; i < colecciones.size(); i++) {
            Coleccion col = colecciones.get(i);
            if (col == null) continue;

            long ingresosTotales = 0;

            for (int j = 0; j < col.getPeliculas().size(); j++) {
                Pelicula peli = col.getPeliculas().get(j);
                if (peli != null) {
                    ingresosTotales += peli.getIngresos();
                }
            }

            heap.put(ingresosTotales, col); // Insertamos en el heap con clave = ingresos
        }

        // Mostrar el top 5
        System.out.println("Top 5 de sagas o películas con más ingresos generados:\n");

        int posicion = 1;
        while (posicion <= 5 && !heap.isEmpty()) {
            try {
                Coleccion coleccion = heap.delete(); // obtenemos solo el valor (Coleccion)
                long ingresos = 0;

                for (int i = 0; i < coleccion.getPeliculas().size(); i++) {
                    ingresos += coleccion.getPeliculas().get(i).getIngresos();
                }

                System.out.println("========== Puesto #" + posicion + " ==========");
                System.out.println("ID: " + coleccion.getId());
                System.out.println("Título: " + coleccion.getNombre());
                System.out.println("Cantidad de películas: " + coleccion.getCantidadPeliculas());
                System.out.print("Películas: [");
                for (int i = 0; i < coleccion.getPeliculas().size(); i++) {
                    System.out.print(coleccion.getPeliculas().get(i).getIdPelicula());
                    if (i < coleccion.getPeliculas().size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
                System.out.println("Ingresos generados: $" + ingresos);
                System.out.println();
                posicion++;
            } catch (EmptyHeapException e) {
                System.out.println("Heap vacío antes de completar el top 5.");
                break;
            }
        }

        System.out.println("Tiempo de ejecución: " + (System.currentTimeMillis() - inicio) + "ms");
    }
}
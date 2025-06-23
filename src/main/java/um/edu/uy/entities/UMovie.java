package um.edu.uy.entities;

import um.edu.uy.exceptions.ElementAlreadyExists;
import um.edu.uy.exceptions.EmptyHeapException;
import um.edu.uy.exceptions.InvalidIndex;
import um.edu.uy.exceptions.ValueNoExists;
import um.edu.uy.tads.hashTable.MyHashTable;
import um.edu.uy.tads.heap.Heap;
import um.edu.uy.tads.heap.MyHeap;
import um.edu.uy.tads.linkedList.MyLinkedList;
import com.opencsv.CSVReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class UMovie implements UmMovieint {

    private CargarDatos charger;
    private MyLinkedList<String> datos;

    //constructor
    public UMovie(CargarDatos cargador) {
        datos = new MyLinkedList<>();
        this.charger = cargador;
    }


    //metodo para cargar los datos
    @Override
    public void cargarDatos() {
        charger.cargarTodo();
    }


    @Override
    public void Top5PeliculasPorIdioma(String idioma) throws IOException, NumberFormatException {
        MyHashTable<Integer, Float> ratingsPromedio = new MyHashTable<>(1000);
        MyHashTable<Integer, Integer> conteoRatings = new MyHashTable<>(1000);
        MyHashTable<Integer, String> listaPeliculas = new MyHashTable<>(1000);
        MyLinkedList<Integer> peliculasDelIdioma = new MyLinkedList<>();


        CSVReader reader;

        // Cargar películas
        charger.cargarPeliculas();
        reader = new CSVReader(new FileReader(charger.Moviecsv)); // Usar la ruta del cargador
        String[] fila;
        reader.readNext(); // saltar encabezado
        int contador = 0;

        String idiomatabla = null;
        int idPelicula = 0;
        String tituloPelicula = null;

        while ((fila = reader.readNext()) != null) {
            if (contador < 5) {
                contador++;
            }
            idiomatabla = fila[7];
            tituloPelicula = fila[8];
            if (idiomatabla.equals(idioma)) {
                try {
                    idPelicula = Integer.parseInt(fila[5]);

                } catch (NumberFormatException e) {
                    continue;
                }

                listaPeliculas.put(idPelicula, tituloPelicula);
                peliculasDelIdioma.add(idPelicula);

            }
        }
        reader.close();


        // Cargar ratings
        charger.cargarRatings();
        reader = new CSVReader(new FileReader(charger.RatingCsv)); // Usar ruta directa
        reader.readNext();

        Heap<Integer, Integer> map = new MyHeap<>(false);
        float ratings = 0;

        while ((fila = reader.readNext()) != null) {

            try {
                idPelicula = Integer.parseInt(fila[1]);
                ratings = Float.parseFloat(fila[2]);

                try {
                    listaPeliculas.get(idPelicula); //Cambio: Verificar que existe

                    try {
                        float sumaActual = ratingsPromedio.get(idPelicula);
                        ratingsPromedio.put(idPelicula, sumaActual + ratings);
                    } catch (ValueNoExists e) {
                        ratingsPromedio.put(idPelicula, ratings);
                    }

                    try {
                        int conteoActual = conteoRatings.get(idPelicula);
                        conteoRatings.put(idPelicula, conteoActual + 1);
                    } catch (ValueNoExists e) {
                        conteoRatings.put(idPelicula, 1);
                    }

                } catch (Exception e) {
                }

            } catch (Exception e) {

            }
        }
        reader.close();


        for (int id = 0; id < peliculasDelIdioma.size(); id++) {
            try {
                int peliculaId = peliculasDelIdioma.get(id);

                float suma = ratingsPromedio.get(peliculaId);

                int conteo = conteoRatings.get(peliculaId);

                float promedio = suma / conteo;

                map.put(peliculaId, conteo);

            } catch (InvalidIndex e) {

            } catch (ValueNoExists e) {

            }

        }


        // Mostrar top 5

        int recorrido = 0;
        int peliculaid;

        while (recorrido < 5 && !map.isEmpty()) {
            peliculaid = map.getKey();


            try {
                tituloPelicula = listaPeliculas.get(peliculaid);
            } catch (ValueNoExists e) {
                throw new RuntimeException(e);
            }
            if (tituloPelicula == null) {
                continue;
            }
            int totalCalificaciones = 0;
            try {
                totalCalificaciones = conteoRatings.get(peliculaid);
            } catch (ValueNoExists e) {
                throw new RuntimeException(e);
            }
            System.out.println(peliculaid + ", " + tituloPelicula + "," + totalCalificaciones + "," + idioma);
            recorrido++; // Solo un incremento por película procesada
            try {
                map.delete();
            } catch (EmptyHeapException e) {
                throw new RuntimeException(e);
            }
        }

    }



    @Override
    public void Top10PeliculasMejorCalificacionPorUsuarios () throws NumberFormatException, IOException {
        MyHashTable<Integer, Float> ratingsPromedio = new MyHashTable<>(1000);
        MyHashTable<Integer, Integer> conteoRatings = new MyHashTable<>(1000);
        MyHashTable<Integer, String> listaPeliculas = new MyHashTable<>(1000);



        CSVReader reader;

        // Cargar películas
        charger.cargarPeliculas();
        reader = new CSVReader(new FileReader(charger.Moviecsv)); // Usar la ruta del cargador
        String[] fila;
        reader.readNext(); // saltar encabezado
        int contador = 0;


        int idPelicula = 0;
        String tituloPelicula = null;

        while ((fila = reader.readNext()) != null) {
            if (contador < 5) {
                contador++;
            }

            tituloPelicula = fila[8];
            try {
                idPelicula = Integer.parseInt(fila[5]);

            } catch (NumberFormatException e) {
                continue;
            }
            listaPeliculas.put(idPelicula, tituloPelicula);


        }
        reader.close();


        // Cargar ratings
        charger.cargarRatings();
        reader = new CSVReader(new FileReader(charger.RatingCsv)); // Usar ruta directa
        reader.readNext();

        Heap<Integer, Integer> map = new MyHeap<>(false);
        float ratings = 0;

        while ((fila = reader.readNext()) != null) {

            try {
                idPelicula = Integer.parseInt(fila[1]);
                ratings = Float.parseFloat(fila[2]);

                try {
                    listaPeliculas.get(idPelicula); //Cambio: Verificar que existe

                        if (ratingsPromedio.contains(idPelicula)) {
                            float sumaActual = ratingsPromedio.get(idPelicula);
                            ratingsPromedio.put(idPelicula, sumaActual + ratings);

                        } else {
                            ratingsPromedio.put(idPelicula, ratings);
                        }




                    int conteoActual = conteoRatings.get(idPelicula);
                    conteoRatings.put(idPelicula, conteoActual + 1);

                } catch (Exception e) {
                }

            } catch (Exception e) {

            }
        }
        reader.close();


//        for (int id = 0; id < listaPeliculas.size(); id++) {
//            try {
//                int peliculaId = listaPeliculas.get(id);
//
//                float suma = ratingsPromedio.get(peliculaId);
//
//                int conteo = conteoRatings.get(peliculaId);
//
//                float promedio = suma / conteo;
//
//                map.put(peliculaId, conteo);
//
//            } catch (ValueNoExists | InvalidIndex e) {
//
//            }
//
//        }
//
//
//        // Mostrar top 5
//
//        int recorrido = 0;
//        int peliculaid;
//
//        while (recorrido < 5 && !map.isEmpty()) {
//            peliculaid = map.getKey();
//
//
//            try {
//                tituloPelicula = listaPeliculas.get(peliculaid);
//                if (tituloPelicula == null) {
//                    continue;
//                }
//                int totalCalificaciones = conteoRatings.get(peliculaid);
//                //System.out.println(peliculaid + ", " + tituloPelicula + "," + totalCalificaciones + "," + idioma);
//                recorrido++; // Solo un incremento por película procesada
//            } catch (ValueNoExists e) {
//                continue;
//            }
//            try {
//                map.delete();
//            } catch (EmptyHeapException e) {
//                throw new RuntimeException(e);
//            }
//        }

    }


    @Override
    public void Top5ColeccionesMasIngresos() {

    }

    @Override
    public void Top10DirectoresMejorCalificacion() {

    }

    @Override
    public void ActorConMasCalificacionesEnCadaMesDelAnio(int anio) {

    }

    @Override
    public void UsuarioConMasCalificacionesPorGenero(String genero) {

    }
}



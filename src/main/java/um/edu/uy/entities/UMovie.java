package um.edu.uy.entities;

import um.edu.uy.exceptions.ElementAlreadyExists;
import um.edu.uy.exceptions.ValueNoExists;
import um.edu.uy.tads.hashTable.MyHashTable;
import um.edu.uy.tads.heap.Heap;
import um.edu.uy.tads.heap.MyHeap;
import um.edu.uy.tads.linkedList.MyLinkedList;
import com.opencsv.CSVReader;


import java.io.FileReader;
import java.io.IOException;


public class UMovie implements UmMovieint {


    private MyLinkedList<String> datos;
    String Moviecsv = "resources/movies_metadata.csv";
    String RatingCsv = "resources/ratings_1mm.csv";


    public UMovie() {
        datos = new MyLinkedList<>();
    }


    @Override
    public void Top_5_peliculas_por_idioma(String idioma) throws IOException {
        System.out.println("cargando datos");
        MyHashTable<Integer, String> nombrePeliculas = new MyHashTable<>(8);
        CSVReader reader = new CSVReader(new FileReader(Moviecsv));

        String[] fila;
        reader.readNext(); // saltar encabezado

        String idiomatabla = null;
        int idPelicula = 0;
        String tituloPelicula = null;


        while ((fila = reader.readNext()) != null) {//Devuelve una lista con las columnas de las filas (elemento 1 primer columna y etc)
            idiomatabla = fila[7];
            tituloPelicula = fila[8];
            try { idPelicula = Integer.parseInt(fila[5]);}
            catch (NumberFormatException e) {
                continue;
            }
            if (idiomatabla.equals(idioma.toLowerCase())) {//El lowercase lo que hace es convertir las entradas en minusucla ya que estan asi en la tabla
                try {
                    nombrePeliculas.put(idPelicula, tituloPelicula);
                } catch (ElementAlreadyExists e) {
                    throw new RuntimeException(e);
                }
            }


        }
        reader.close();
        reader = new CSVReader(new FileReader(RatingCsv));

        float ratings = 0;
        Heap<Integer, Float> mapa = new MyHeap<>(false);
        reader.readNext(); // saltar encabezado


        while ((fila = reader.readNext()) != null) {//Devuelve una lista con las columnas de las filas (elemento 1 primer columna y etc)
            try { idPelicula = Integer.parseInt(fila[1]);}
            catch (NumberFormatException e) {
                continue;}
            ratings = Float.parseFloat(fila[2]) ;


            mapa.put(idPelicula, ratings);



        }
        reader.close();
        //Aca hacemos un while y sacamos los 5 con mas ratings por idioma


        int recorrido = 0;
        int peliculaid;
        float rating;

        while (recorrido<5){
            peliculaid = mapa.getKey();
            rating = mapa.getValue();
            try {
                tituloPelicula = nombrePeliculas.get(peliculaid);
            } catch (ValueNoExists e) {
            }
            System.out.print("peliculaid: " + peliculaid );//Borramos el ln por el formato que pide la letra
            System.out.print("tituloPelicula" + tituloPelicula);
            System.out.println("rating" + rating );
            recorrido++;
        }

    }







    @Override
    public void Top_10_peliculas_mejor_calificacion_por_usuarios() {

    }

    @Override
    public void Top_5_colecciones_mas_ingresos() {

    }

    @Override
    public void Top_10_directores_mejor_calificacion() {

    }

    @Override
    public void Actor_con_mas_calificaciones_en_cada_mes_del_anio(int anio) {

    }

    @Override
    public void Usuario_con_mas_calificaciones_por_genero(String genero) {

    }
}

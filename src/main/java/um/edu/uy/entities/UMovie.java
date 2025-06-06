package um.edu.uy.entities;

import um.edu.uy.tads.hashTable.MyHashTable;
import um.edu.uy.tads.linkedList.MyLinkedList;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;


public class UMovie implements UmMovieint {
    private MyLinkedList<String> datos;
    String Moviecsv = "resources/movies_metadata.csv";
    String RatingCsv = "resources/ratings.csv";


    public UMovie() {
        datos = new MyLinkedList<>();


    }


    @Override
    public void Top_5_peliculas_por_idioma(String idioma) throws IOException {
        System.out.println("cargando datos");
        MyLinkedList<Integer> idPeliculaS = new MyLinkedList<>();
        CSVReader reader = new CSVReader(new FileReader(Moviecsv));

        String[] fila;
        reader.readNext(); // saltar encabezado

        String idiomatabla = null;
        int idPelicula = 0;

        while ((fila = reader.readNext()) != null) {//Devuelve una lista con las columnas de las filas (elemento 1 primer columna y etc)
            idiomatabla = fila[7];
            idPelicula = Integer.parseInt(fila[5]);
            System.out.println(idPelicula);
            if (idiomatabla.equals(idioma.toLowerCase())) {//El lowercase lo que hace es convertir las entradas en minusucla ya que estan asi en la tabla
                idPeliculaS.add(idPelicula);
            }


        }
        reader.close();
        reader = new CSVReader(new FileReader(RatingCsv));

        float ratings = 0;
        MyHashTable<Float, Integer>  mapa = new  MyHashTable<>(8); //Cambiar por heap
        reader.readNext(); // saltar encabezado


        while ((fila = reader.readNext()) != null) {//Devuelve una lista con las columnas de las filas (elemento 1 primer columna y etc)
            idPelicula = Integer.parseInt(fila[1]);
            ratings = Float.parseFloat(fila[2]) ;

            mapa.put(ratings, idPelicula);


        }
        reader.close();

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

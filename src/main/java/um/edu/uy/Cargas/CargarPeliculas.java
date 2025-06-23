package um.edu.uy.Cargas;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import um.edu.uy.Exceptions.ElementAlreadyExists;
import um.edu.uy.Exceptions.InvalidIndex;
import um.edu.uy.Exceptions.ValueNoExists;
import um.edu.uy.TADS.LinkedList.LinkedList;
import um.edu.uy.TADS.HashTable.MyHashTable;
import um.edu.uy.TADS.HashTable.HashTable;
import um.edu.uy.TADS.LinkedList.MyLinkedList;
import um.edu.uy.Entities.Coleccion;
import um.edu.uy.Entities.Genero;
import um.edu.uy.Entities.Idioma;
import um.edu.uy.Entities.Pelicula;

public class CargarPeliculas {
    private CSVReader lectorCSV;
    private final HashTable<Integer, Pelicula> peliculas;
    private final HashTable<Integer, Genero> generos;
    private final HashTable<String, Idioma> idiomas;
    private final HashTable<Integer, Coleccion> colecciones;
    private final Pattern patternColeccion = Pattern.compile("'id':\\s*(\\d+),\\s*'name':\\s*'([^']+)'");
    private final Pattern patternGenero = Pattern.compile("'id':\\s*(\\d+),\\s*'name':\\s*'([^']+)'");

    public CargarPeliculas() {
        try{
           FileInputStream archivoCSV = new FileInputStream("movies_metadata.csv");
           this.lectorCSV = new CSVReader(new InputStreamReader(archivoCSV));
            lectorCSV.readNext(); // Se lee la primera línea (cabecera) y se descarta
        } catch (IOException | CsvValidationException ignored) { //No deberia de ocurrir, pero si ocurre, se imprime el error
            System.out.println("Error critico al cargar el archivo de peliculas. Asegurese de que el archivo movies_metadata.csv se encuentre en la carpeta resources del proyecto.");
        }

        this.peliculas = new MyHashTable<>(59999);
        this.generos = new MyHashTable<>(53);
        this.idiomas = new MyHashTable<>(97);
        this.colecciones = new MyHashTable<>(1709);

        try{
            cargarDatos();
        } catch (IOException | CsvValidationException | InvalidIndex ignored) {
            System.out.println("Error al cargar los datos de las peliculas"); // No deberia de ocurrir, pero si ocurre, se imprime el error
        } catch (ValueNoExists e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarDatos() throws IOException, CsvValidationException, InvalidIndex, ValueNoExists {

        String[] dataLine;
        while ((dataLine = lectorCSV.readNext()) != null) {

            int idPelicula;
            try {
                idPelicula = Integer.parseInt(dataLine[5]);
            } catch (NumberFormatException e) {
                continue;
            }

            long ganancias = 0;
            try {
                ganancias = Long.parseLong(dataLine[13]);
            } catch (NumberFormatException ignored) {}

            Pelicula pelicula = new Pelicula(idPelicula, dataLine[8], dataLine[12], ganancias);
            try {
                peliculas.put(idPelicula, pelicula);
            } catch (ElementAlreadyExists ignored) {
                continue;
            }

            LinkedList<Genero> listaGeneros = searchGeneros(dataLine[3]);

            for (int iter = 0; iter < listaGeneros.size(); iter++) {
                Genero genero = listaGeneros.get(iter); // declaramos bien el objeto desde la lista

                try {
                    this.generos.put(genero.getId(), genero);
                    genero.agregarPelicula(pelicula);
                } catch (ElementAlreadyExists e) {
                    try {
                        Genero yaExistente = generos.get(genero.getId());
                        yaExistente.agregarPelicula(pelicula);
                    } catch (ValueNoExists ignored) {
                        // Esto no debería pasar si put falló por existir, pero lo manejamos por seguridad
                    }
                }
            }

            String acronimoIdioma = dataLine[7];
            if (acronimoIdioma != null && !acronimoIdioma.trim().isEmpty()) {
                Idioma idioma = new Idioma(acronimoIdioma);
                try {
                    idiomas.put(acronimoIdioma, idioma);
                    idioma.agregarPelicula(pelicula);
                } catch (ElementAlreadyExists ignored) {
                    idioma = idiomas.get(acronimoIdioma);
                    idioma.agregarPelicula(pelicula);
                }
            }

            Coleccion coleccion = searchColecciones(dataLine[1], idPelicula, dataLine[8]);
            if (coleccion != null){
                try {
                    colecciones.put(coleccion.getId(), coleccion);
                    coleccion.agregarPelicula(pelicula);
                } catch (ElementAlreadyExists ignored) {
                    coleccion = colecciones.get(coleccion.getId());
                    coleccion.agregarPelicula(pelicula);
                }
            }
        }

    }

    public HashTable<Integer, Pelicula> getPeliculas() {
        return peliculas;
    }

    public HashTable<Integer, Genero> getGeneros() {
        return generos;
    }

    public HashTable<String, Idioma> getIdiomas() {
        return idiomas;
    }

    public HashTable<Integer, Coleccion> getColecciones() {
        return colecciones;
    }

    private LinkedList<Genero> searchGeneros(String entrada){
        LinkedList<Genero> listaGeneros = new MyLinkedList<>();
        if (entrada == null || entrada.trim().isEmpty()) {
            return listaGeneros;
        }

        Matcher matcher = patternGenero.matcher(entrada);
        while (matcher.find()) {
            try {
                int id = Integer.parseInt(matcher.group(1));
                String nombre = matcher.group(2);
                listaGeneros.add(new Genero(id, nombre));
            } catch (NumberFormatException ignored) {}
        }
        return listaGeneros;

    }

    private Coleccion searchColecciones(String entrada, int idPelicula, String nombrePelicula){
        if (entrada == null) {
            return null;
        } else if(entrada.trim().isEmpty()){
            return new Coleccion(idPelicula, nombrePelicula);
        }

        Matcher matcher = patternColeccion.matcher(entrada);
        if (matcher.find()){
            try {
                int id = Integer.parseInt(matcher.group(1));
                String nombre = matcher.group(2);
                return new Coleccion(id, nombre);
            } catch (NumberFormatException ignored) {
                return null;
            }
        }
        return null;
    }

    private void mostrarEstadisticasCarga(long inicio, long fin){
        System.out.println("\nResultados de la carga:");
        System.out.println("Tiempo total de carga: " + (fin - inicio) + " milisegundos");
        System.out.println("Peliculas procesadas: " + (lectorCSV.getRecordsRead()-1));
        System.out.println("Peliculas validas cargadas: " + peliculas.size());
        System.out.println("Generos unicos: " + generos.size());
        System.out.println("Idiomas unicos: " + idiomas.size());
        System.out.println("Colecciones unicas: " + colecciones.size());
    }
}
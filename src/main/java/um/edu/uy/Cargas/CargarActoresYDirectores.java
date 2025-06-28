package um.edu.uy.Cargas;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import um.edu.uy.Entities.Actor;
import um.edu.uy.Entities.Director;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Exceptions.ValueNoExists;
import um.edu.uy.TADS.HashTable.MyHash;
import um.edu.uy.TADS.HashTable.MyHashTable;

import java.io.*;

public class CargarActoresYDirectores {
    private CSVReader lectorCSV;
    private final MyHash<String, Director> directores;
    private final MyHash<String, Actor> actores;

    private static final String TRABAJO_DIRECTOR = "'job': 'Director'";
    private static final String CLAVE_NOMBRE = "'name': '";
    private static final String CLAVE_NOMBRE_ACTOR = "'name': '";

    public CargarActoresYDirectores() {
        this.directores = new MyHashTable<>(59999);
        this.actores = new MyHashTable<>(59999);

        try {
            FileInputStream archivoCSV = new FileInputStream("resources/credits.csv");
            this.lectorCSV = new CSVReader(new InputStreamReader(archivoCSV));
            this.lectorCSV.readNext(); // Se lee la primera línea (cabecera) y se descarta
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error al cargar el archivo credits.csv: " + e.getMessage());
        }
    }

    public void cargarDatos(MyHash<Integer, Pelicula> listaDePeiculas) throws CsvValidationException, IOException, ValueNoExists {
        if (lectorCSV == null) {
            System.out.println("Error: No se pudo inicializar el lector CSV");
            return;
        }

        String[] dataLine;
        int lineasProcesadas = 0;

        while ((dataLine = lectorCSV.readNext()) != null) {
            lineasProcesadas++;

            if (dataLine.length < 3) continue;

            int idPelicula;
            try {
                idPelicula = Integer.parseInt(dataLine[2].trim());
            } catch (NumberFormatException e) {
                continue;
            }

            Pelicula pelicula = listaDePeiculas.get(idPelicula);
            if (pelicula == null) continue;

            String actoresRaw = dataLine[0];
            if (actoresRaw != null && !actoresRaw.isEmpty()) {
                procesarActores(actoresRaw, pelicula);
            }

            String equipoRaw = dataLine[1];
            if (equipoRaw != null && equipoRaw.contains("Director")) {
                procesarDirectores(equipoRaw, pelicula);
            }
        }
    }

    public MyHash<String, Director> getDirectores() {
        return directores;
    }

    public MyHash<String, Actor> getActores() {
        return actores;
    }

    private void procesarActores(String entrada, Pelicula tempPeli) throws ValueNoExists {
        int posicionInicial = 0;
        int longitud = entrada.length();
        MyHash<String, Boolean> actoresVistos = new MyHashTable<>(100);

        while (posicionInicial < longitud) {
            int inicioNombre = entrada.indexOf(CLAVE_NOMBRE_ACTOR, posicionInicial);
            if (inicioNombre == -1) break;

            inicioNombre += CLAVE_NOMBRE_ACTOR.length();
            int finNombre = entrada.indexOf("'", inicioNombre);

            if (finNombre == -1 || finNombre <= inicioNombre) {
                posicionInicial = inicioNombre;
                continue;
            }

            String nombreActor = entrada.substring(inicioNombre, finNombre);

            if (nombreActor.length() >= 3 && actoresVistos.get(nombreActor) == null) {
                actoresVistos.put(nombreActor, true);
                Actor actor = actores.get(nombreActor);
                if (actor == null) {
                    actor = new Actor(nombreActor);
                    actores.put(nombreActor, actor);
                }
                actor.agregarPelicula(tempPeli);
            }

            posicionInicial = finNombre + 1;
        }
    }

    private void procesarDirectores(String entrada, Pelicula tempPeli) throws ValueNoExists {
        int posicion = 0;
        String patronJob = "'job': 'Director'";
        String patronName = "'name': '";

        while ((posicion = entrada.indexOf(patronJob, posicion)) != -1) {
            // Buscar el nombre más cercano (antes o después)
            int mejorPos = -1;
            int mejorDistancia = Integer.MAX_VALUE;
            String mejorNombre = null;

            // Buscar en un rango de 300 chars alrededor del job
            int inicio = Math.max(0, posicion - 300);
            int fin = Math.min(entrada.length() - patronName.length(), posicion + 300);

            for (int i = inicio; i <= fin; i++) {
                if (entrada.substring(i, i + patronName.length()).equals(patronName)) {
                    int startName = i + patronName.length();
                    int endName = entrada.indexOf("'", startName);

                    if (endName != -1) {
                        String nombre = entrada.substring(startName, endName);
                        int distancia = Math.abs(i - posicion);

                        if (distancia < mejorDistancia && nombre.length() >= 2) {
                            mejorDistancia = distancia;
                            mejorPos = i;
                            mejorNombre = nombre;
                        }
                    }
                }
            }

            if (mejorNombre != null) {
                Director director = directores.get(mejorNombre);
                if (director == null) {
                    director = new Director(mejorNombre);
                    directores.put(mejorNombre, director);
                }
                director.agregarPelicula(tempPeli);
            }

            posicion += patronJob.length();
        }
    }
}
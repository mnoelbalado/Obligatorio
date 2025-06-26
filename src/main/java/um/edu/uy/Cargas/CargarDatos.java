package um.edu.uy.Cargas;

import com.opencsv.exceptions.CsvValidationException;
import um.edu.uy.Entities.Coleccion;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Exceptions.ValueNoExists;
import um.edu.uy.TADS.HashTable.MyHash;

import java.io.IOException;


public class CargarDatos {
    private CargarPeliculas cargaPeliculas;
    private CargarRatings cargaRatings;
    private CargarActoresYDirectores cargarActoresYDirectores;
    private boolean datosCargados = false;

    public void cargarDatos() {
        cargaPeliculas = new CargarPeliculas();
        cargaRatings = new CargarRatings();

        try {
            cargaRatings.cargarRatingsAPeliculas(cargaPeliculas.getPeliculas());
        } catch (CsvValidationException | IOException | ValueNoExists e) {
            throw new RuntimeException(e);
        }

        // Si implementás esto más adelante:
        // cargarActoresYDirectores = new CargarActoresYDirectores();

        datosCargados = true;
    }

    // Getter para cargarPeliculas
    public CargarPeliculas getCargaPeliculas() {
        return cargaPeliculas;
    }

    // Getter directo para películas (acceso externo más cómodo)
    public MyHash<Integer, Pelicula> getPeliculas() {
        return cargaPeliculas.getPeliculas();
    }

    // Getter directo para colecciones
    public MyHash<Integer, Coleccion> getColecciones() {
        return cargaPeliculas.getColecciones();
    }

    // Getter para ratings y actores/directores (si los usás)
    public CargarRatings getCargaRatings() {
        return cargaRatings;
    }

    public CargarActoresYDirectores getCargarActoresYDirectores() {
        return cargarActoresYDirectores;
    }

    public boolean isDatosCargados() {
        return datosCargados;
    }

    public boolean cargado() {
        return datosCargados;
    }
}
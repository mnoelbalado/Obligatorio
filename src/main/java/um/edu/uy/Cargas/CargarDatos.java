package um.edu.uy.Cargas;

import com.opencsv.exceptions.CsvValidationException;
import um.edu.uy.Exceptions.ValueNoExists;

import java.io.IOException;

public class CargarDatos {
    public CargarPeliculas  cargaPeliculas;
    public CargarRatings cargaRatings;
    public CargarActoresYDirectores cargarActoresYDirectores;

    public CargarDatos() {
        cargaPeliculas = new CargarPeliculas();
        cargaRatings = new CargarRatings();
        cargarActoresYDirectores = new CargarActoresYDirectores();
        try {
            cargaRatings.cargarRatingsAPeliculas(cargaPeliculas.getPeliculas());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ValueNoExists e) {
            throw new RuntimeException(e);
        }
    }
}

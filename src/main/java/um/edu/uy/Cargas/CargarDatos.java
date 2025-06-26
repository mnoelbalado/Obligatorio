package um.edu.uy.Cargas;

import com.opencsv.exceptions.CsvValidationException;
import um.edu.uy.Exceptions.ValueNoExists;

import java.io.IOException;

public class CargarDatos {
    private CargarPeliculas  cargaPeliculas;
    private CargarRatings cargaRatings;
    private CargarActoresYDirectores cargarActoresYDirectores;
    private boolean datosCargados = false;

    public void cargarDatos(){
        cargaPeliculas = new CargarPeliculas();
        cargaRatings = new CargarRatings();


        //cargarActoresYDirectores = new CargarActoresYDirectores(); TERMINAR
        try {
            cargaRatings.cargarRatingsAPeliculas(cargaPeliculas.getPeliculas());
        } catch (CsvValidationException | IOException | ValueNoExists e) {
            throw new RuntimeException(e);
        }
        datosCargados = true;
    }

    public CargarPeliculas getCargaPeliculas() {
        return cargaPeliculas;
    }

    public CargarRatings getCargaRatings() {
        return cargaRatings;
    }

    public CargarActoresYDirectores getCargarActoresYDirectores() {
        return cargarActoresYDirectores;
    }

    public boolean isDatosCargados() {
        return datosCargados;
    }

    public boolean cargado(){return datosCargados;}
}

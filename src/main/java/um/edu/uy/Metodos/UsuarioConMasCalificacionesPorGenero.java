package um.edu.uy.Metodos;

import um.edu.uy.Cargas.CargarDatos;

public class UsuarioConMasCalificacionesPorGenero implements Consulta {
    CargarDatos datos;
    public UsuarioConMasCalificacionesPorGenero(CargarDatos datos){
        this.datos = datos;
    }

    @Override
    public void realizarConsulta() {

    }
}

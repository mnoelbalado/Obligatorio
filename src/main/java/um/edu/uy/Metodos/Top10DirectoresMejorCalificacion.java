package um.edu.uy.Metodos;

import um.edu.uy.Cargas.CargarDatos;

public class Top10DirectoresMejorCalificacion implements Consulta{
    CargarDatos datos;
    public Top10DirectoresMejorCalificacion(CargarDatos datos){
        this.datos = datos;
    }

    @Override
    public void realizarConsulta() {

    }
}

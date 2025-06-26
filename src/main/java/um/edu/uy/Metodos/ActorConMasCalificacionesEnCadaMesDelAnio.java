package um.edu.uy.Metodos;

import um.edu.uy.Cargas.CargarDatos;

public class ActorConMasCalificacionesEnCadaMesDelAnio implements Consulta {
    CargarDatos datos;
    public ActorConMasCalificacionesEnCadaMesDelAnio(CargarDatos datos){
        this.datos = datos;
    }

    @Override
    public void realizarConsulta() {

    }
}

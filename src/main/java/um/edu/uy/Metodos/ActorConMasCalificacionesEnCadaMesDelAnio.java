package um.edu.uy.Metodos;

import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Genero;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Entities.Usuario;
import um.edu.uy.TADS.HashTable.MyHash;
import um.edu.uy.TADS.HashTable.MyHashTable;
import um.edu.uy.TADS.Heap.MyHeap;
import um.edu.uy.TADS.LinkedList.LinkedList;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class ActorConMasCalificacionesEnCadaMesDelAnio implements Consulta {
    CargarDatos datos;
    public ActorConMasCalificacionesEnCadaMesDelAnio(CargarDatos datos){
        this.datos = datos;
    }

    @Override
    public void realizarConsulta() {

    }
}


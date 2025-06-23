package um.edu.uy.Entities;

import um.edu.uy.TADS.LinkedList.LinkedList;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class Actor {
    private String nombre;
    private final LinkedList<Pelicula> peliculas;

public Actor(String nombre) {
    this.nombre = nombre;
    this.peliculas = new MyLinkedList<>(); // tu implementación concreta
}

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public LinkedList<Pelicula> getPeliculas() {
        return peliculas;
    }
    public void agregarPelicula(Pelicula tempPeli) {
        peliculas.add(tempPeli);
    }

    public int getCantidadPeliculasActor() {
        return peliculas.size();
    }
}

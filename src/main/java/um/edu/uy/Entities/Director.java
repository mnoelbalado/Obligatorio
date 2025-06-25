package um.edu.uy.Entities;

import um.edu.uy.TADS.LinkedList.LinkedList;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class Director {
    private String nombre;
    private LinkedList<Pelicula> listaPeliculas;

    public Director(String nombres) {
        this.nombre = nombre;
        this.listaPeliculas = new MyLinkedList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public void agregarPelicula(Pelicula tempPelicula) {
        this.listaPeliculas.add(tempPelicula);
    }

    public int getCantidadPeliculas(){
        return listaPeliculas.size();
    }

}

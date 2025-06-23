package um.edu.uy.entities;

import um.edu.uy.tads.linkedList.LinkedList;
import um.edu.uy.tads.linkedList.MyLinkedList;


public class Genero {
    private int id;
    private String nombre;
    private LinkedList<Pelicula> listaPeliculas;

    public Genero(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.listaPeliculas = new MyLinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setListaPeliculas(LinkedList<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    public void agregarPelicula(Pelicula tempPeli) {
        listaPeliculas.add(tempPeli);
    }


}

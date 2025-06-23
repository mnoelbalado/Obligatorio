package um.edu.uy.entities;

import um.edu.uy.tads.linkedList.LinkedList;
import um.edu.uy.tads.linkedList.MyLinkedList;

public class Idioma {
    private String nombre;
    private String acronimo;
    private LinkedList<Pelicula> listaPeliculas;

    public Idioma(String acronimo) {
        this.nombre = null;
        this.acronimo = acronimo;
        this.listaPeliculas = new MyLinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
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


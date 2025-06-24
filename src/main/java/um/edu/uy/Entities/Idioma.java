package um.edu.uy.Entities;

import um.edu.uy.TADS.LinkedList.LinkedList;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class Idioma {
    private String acronimo;
    private LinkedList<Pelicula> listaPeliculas;

    public Idioma(String acronimo) {
        this.acronimo = acronimo;
        this.listaPeliculas = new MyLinkedList<>();
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


package um.edu.uy.Entities;

import um.edu.uy.TADS.LinkedList.LinkedList;

public class Director {
    private String nombre;
    private final LinkedList<Pelicula> listaPeliculas;

    public Director(String nombre, LinkedList<Pelicula> listaPeliculas) {
        this.nombre = nombre;
        this.listaPeliculas = listaPeliculas;

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

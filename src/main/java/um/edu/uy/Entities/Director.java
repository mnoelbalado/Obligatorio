package um.edu.uy.Entities;

import um.edu.uy.TADS.LinkedList.LinkedList;

public class Director {
    private String nombre;
    private final LinkedList<Pelicula> listaPeliculas;

    public Director(LinkedList<Pelicula> listaPeliculas, String nombre) {
        this.listaPeliculas = listaPeliculas;
        this.nombre = nombre;
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

package um.edu.uy.Entities;

import um.edu.uy.TADS.LinkedList.LinkedList;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class Director {
    private String nombre;
    private LinkedList<Pelicula> listaPeliculas;
    private int cantidadCalificaciones;
    private double sumaCalificaciones;

    public Director(String nombres) {
        this.nombre = nombre;
        this.listaPeliculas = new MyLinkedList<>();
        this.cantidadCalificaciones = 0;
        this.sumaCalificaciones = 0;

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

    public int getCantidadCalificaciones() {
        return cantidadCalificaciones;
    }

    public void setCantidadCalificaciones(int cantidadCalificaciones) {
        this.cantidadCalificaciones = cantidadCalificaciones;
    }

    public double getSumaCalificaciones() {
        return sumaCalificaciones;
    }

    public void setSumaCalificaciones(double sumaCalificaciones) {
        this.sumaCalificaciones = sumaCalificaciones;
    }

    public double obtenerPromedio() {
        if (cantidadCalificaciones == 0) {
            return 0.0; // Evita división por cero
        }
        return sumaCalificaciones / cantidadCalificaciones;
    }
}

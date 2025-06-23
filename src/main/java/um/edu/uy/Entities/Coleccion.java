package um.edu.uy.Entities;

import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class Coleccion {
    private int id;
    private String nombre;
    private MyLinkedList<Pelicula> peliculas;

    private int cantidadPeliculas;
    private double ingresosTotales;

    //constructor
    public Coleccion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.peliculas = new MyLinkedList<>();
        this.cantidadPeliculas = 0;
        this.ingresosTotales = 0;
    }

    public void agregarPelicula(Pelicula nombre) {
        peliculas.add(nombre);
        cantidadPeliculas++;
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

    public MyLinkedList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(MyLinkedList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public int getCantidadPeliculas() {
        return cantidadPeliculas;
    }

    public void setCantidadPeliculas(int cantidadPeliculas) {
        this.cantidadPeliculas = cantidadPeliculas;
    }

    public double getIngresosTotales() {
        return ingresosTotales;
    }

    public void setIngresosTotales(double ingresosTotales) {
        this.ingresosTotales = ingresosTotales;
    }
}


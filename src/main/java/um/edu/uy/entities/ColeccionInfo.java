package um.edu.uy.entities;

import um.edu.uy.tads.linkedList.MyLinkedList;

public class ColeccionInfo {
    private int id; // Puede ser idSaga o idPelicula (si es individual)
    private String nombre;
    private MyLinkedList<Pelicula> peliculas;
    private int cantidadPeliculas;
    private double ingresosTotales;

    public ColeccionInfo(int id, String nombre) {
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

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public MyLinkedList<Pelicula> getIdsPeliculas() { return peliculas; }
    public int getCantidadPeliculas() { return cantidadPeliculas; }
    public double getIngresosTotales() { return ingresosTotales; }
}

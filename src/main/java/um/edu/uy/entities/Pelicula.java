package um.edu.uy.entities;

import um.edu.uy.tads.linkedList.MyLinkedList;

public class Pelicula {
    private String idPelicula;
    private String titulo;
    private String idiomaOriginal;
    private double ingresos;
    private MyLinkedList<String> generos;

    //constructor
    public Pelicula(String idPelicula, String titulo, String idiomaOriginal, double ingresos, MyLinkedList<String> generos) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.idiomaOriginal = idiomaOriginal;
        this.ingresos = ingresos;
        this.generos = generos;
    }

    //getters y setters
    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public MyLinkedList<String> getGeneros() {
        return generos;
    }

    public void setGeneros(MyLinkedList<String> generos) {
        this.generos = generos;
    }
}

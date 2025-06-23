package um.edu.uy.Entities;

import um.edu.uy.TADS.LinkedList.LinkedList;

public class Pelicula {
    private int idPelicula;
    private String titulo;
    private String idiomaOriginal;
    private double ingresos;
    private LinkedList<Rating> listaRating;

//    //necesito para el tercer metodo:
//    private String idSaga;
//    private String tituloSaga;

    //constructor
    public Pelicula(int idPelicula, String titulo, String idiomaOriginal, double ingresos) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.idiomaOriginal = idiomaOriginal;
        this.ingresos = ingresos;

    }

    //getters y setters
    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
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

    public void agregarRating(Rating rating) {
        listaRating.add(rating);
    }
}

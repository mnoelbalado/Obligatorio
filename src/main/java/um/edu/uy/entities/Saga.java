package um.edu.uy.entities;

import um.edu.uy.tads.linkedList.MyLinkedList;

public class Saga {
    private String idSaga ;
    private String titulo;
    private double ingresosTotales;
    private int cantidadPeliculas;
    private MyLinkedList<String> idsPeliculas;

    //constructor
    public Saga(String idSaga, MyLinkedList<String> idsPeliculas, int cantidadPeliculas, double ingresosTotales, String titulo) {
        this.idSaga = idSaga;
        this.idsPeliculas = idsPeliculas;
        this.cantidadPeliculas = cantidadPeliculas;
        this.ingresosTotales = ingresosTotales;
        this.titulo = titulo;
    }

    //getters y setters
    public String getIdSaga() {
        return idSaga;
    }

    public void setIdSaga(String idSaga) {
        this.idSaga = idSaga;
    }

    public MyLinkedList<String> getIdsPeliculas() {
        return idsPeliculas;
    }

    public void setIdsPeliculas(MyLinkedList<String> idsPeliculas) {
        this.idsPeliculas = idsPeliculas;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

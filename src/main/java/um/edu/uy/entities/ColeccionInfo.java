package um.edu.uy.entities;

import um.edu.uy.tads.linkedList.MyLinkedList;

public class ColeccionInfo {
    private String id; // Puede ser idSaga o idPelicula (si es individual)
    private String nombre;
    private MyLinkedList<String> idsPeliculas;
    private int cantidadPeliculas;
    private double ingresosTotales;

    public ColeccionInfo(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.idsPeliculas = new MyLinkedList<>();
        this.cantidadPeliculas = 0;
        this.ingresosTotales = 0;
    }

    public void agregarPelicula(String idPeli, double ingresos) {
        idsPeliculas.add(idPeli);
        cantidadPeliculas++;
        ingresosTotales += ingresos;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public MyLinkedList<String> getIdsPeliculas() { return idsPeliculas; }
    public int getCantidadPeliculas() { return cantidadPeliculas; }
    public double getIngresosTotales() { return ingresosTotales; }
}

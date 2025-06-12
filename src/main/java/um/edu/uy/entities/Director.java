package um.edu.uy.entities;

public class Director {
    private String nombre;
    private int cantidadPeliculas;
    private int cantidadCalificaciones;

    public Director(String nombre, int cantidadPeliculas, int cantidadCalificaciones) {
        this.nombre = nombre;
        this.cantidadPeliculas = cantidadPeliculas;
        this.cantidadCalificaciones = cantidadCalificaciones;
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadPeliculas() {
        return cantidadPeliculas;
    }

    public void setCantidadPeliculas(int cantidadPeliculas) {
        this.cantidadPeliculas = cantidadPeliculas;
    }

    public int getCantidadCalificaciones() {
        return cantidadCalificaciones;
    }

    public void setCantidadCalificaciones(int cantidadCalificaciones) {
        this.cantidadCalificaciones = cantidadCalificaciones;
    }
}

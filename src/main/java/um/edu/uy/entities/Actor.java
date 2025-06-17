package um.edu.uy.entities;

public class Actor {
    private String nombre;

    //constructor
    public Actor(String nombre) {
        this.nombre = nombre;
    }

    //getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

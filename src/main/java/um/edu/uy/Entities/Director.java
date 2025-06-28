package um.edu.uy.Entities;

import um.edu.uy.TADS.LinkedList.LinkedList;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class Director {
    private String nombre;
    private LinkedList<Pelicula> listaPeliculas;
    private int cantidadCalificaciones;
    private double sumaCalificaciones;

    public Director(String nombres) {
        this.nombre = nombres;
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


        if (tempPelicula != null && tempPelicula.getRatings() != null) {
            int calificacionesPelicula = tempPelicula.getRatings().size();
            double sumaPelicula = 0.0;

            // Calcular suma de calificaciones de esta película
            for (int i = 0; i < calificacionesPelicula; i++) {
                try {
                    Rating rating = tempPelicula.getRatings().get(i);
                    if (rating != null) {
                        sumaPelicula += rating.getRating();
                    }
                } catch (Exception e) {

                }
            }

            // Sumar al total del director
            this.cantidadCalificaciones += calificacionesPelicula;
            this.sumaCalificaciones += sumaPelicula;
        }
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


    public void recalcularCalificaciones() {
        this.cantidadCalificaciones = 0;
        this.sumaCalificaciones = 0.0;

        for (int i = 0; i < listaPeliculas.size(); i++) {
            Pelicula pelicula = listaPeliculas.get(i);
            if (pelicula != null && pelicula.getRatings() != null) {
                int calificacionesPelicula = pelicula.getRatings().size();

                for (int j = 0; j < calificacionesPelicula; j++) {
                    try {
                        Rating rating = pelicula.getRatings().get(j);
                        if (rating != null) {
                            this.sumaCalificaciones += rating.getRating();
                        }
                    } catch (Exception e) {
                        // Continuar
                    }
                }

                this.cantidadCalificaciones += calificacionesPelicula;
            }
        }
    }
}
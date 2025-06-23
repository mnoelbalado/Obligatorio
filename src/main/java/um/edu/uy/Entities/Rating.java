package um.edu.uy.Entities;

import java.util.Date;

public class Rating {
    private int idPelciula;
    private double rating;
    private int idUsuario;
    private Date fecha;

    //constructor

    public Rating(int idPelciula, double rating, int idUsuario, Date fecha) {
        this.idPelciula = idPelciula;
        this.rating = rating;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
    }
}

//    //getters y setters
//    public String getIdPelciula() {
//        return idPelciula;
//    }
//
//    public void setIdPelciula(String idPelciula) {
//        this.idPelciula = idPelciula;
//    }
//
//    public double getRating() {
//        return rating;
//    }
//
//    public void setRating(double rating) {
//        this.rating = rating;
//    }
//
//    public String getIdUsuario() {
//        return idUsuario;
//    }
//
//    public void setIdUsuario(String idUsuario) {
//        this.idUsuario = idUsuario;
//    }
//
//    public String getFecha() {
//        return fecha;
//    }
//
//    public void setFecha(String fecha) {
//        this.fecha = fecha;
//    }


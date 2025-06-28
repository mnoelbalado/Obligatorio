package um.edu.uy.Entities;

import java.util.Date;

public class Rating {
    private int idPelciula;
    private float rating;
    private int idUsuario;
    private Date fecha;

    //constructor
    public Rating(int idPelciula, float rating, int idUsuario, Date fecha) {
        this.idPelciula = idPelciula;
        this.rating = rating;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
    }

    public int getIdPelciula() {
        return idPelciula;
    }

    public void setIdPelciula(int idPelciula) {
        this.idPelciula = idPelciula;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

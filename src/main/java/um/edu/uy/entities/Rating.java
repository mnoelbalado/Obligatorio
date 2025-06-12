package um.edu.uy.entities;

public class Rating {
    String idPelciula;
    double rating;
    String idUsuario;
    String fecha;

    //constructor
    public Rating(String idPelciula, double rating, String idUsuario, String fecha) {
        this.idPelciula = idPelciula;
        this.rating = rating;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
    }

    //getters y setters
    public String getIdPelciula() {
        return idPelciula;
    }

    public void setIdPelciula(String idPelciula) {
        this.idPelciula = idPelciula;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

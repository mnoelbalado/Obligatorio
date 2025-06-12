package um.edu.uy.entities;

public class Usuario {
       private String idUsuario;
       private String nombre;

       //constructor
       public Usuario(String idUsuario, String nombre) {
              this.idUsuario = idUsuario;
              this.nombre = nombre;
       }

       //getters y setters
       public String getIdUsuario() {
              return idUsuario;
       }

       public void setIdUsuario(String idUsuario) {
              this.idUsuario = idUsuario;
       }

       public String getNombre() {
              return nombre;
       }

       public void setNombre(String nombre) {
              this.nombre = nombre;
       }
}

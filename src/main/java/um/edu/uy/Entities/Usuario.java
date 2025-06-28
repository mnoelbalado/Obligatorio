package um.edu.uy.Entities;

import um.edu.uy.TADS.HashTable.MyHash;
import um.edu.uy.TADS.HashTable.MyHashTable;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

public class Usuario {
       private int idUsuario;
       private MyLinkedList<Rating> ratings;

       //constructor
       public Usuario(int idUsuario) {
              this.idUsuario = idUsuario;
              this.ratings = new MyLinkedList<>();
       }

       //getters y setters
       public int getIdUsuario() {
              return idUsuario;
       }

       public void setIdUsuario(int idUsuario) {
              this.idUsuario = idUsuario;
       }

       public void agregarRating(Rating rating) {
              ratings.add(rating);
       }

       public MyLinkedList<Rating> getRatings() {
              return ratings;
       }

       public int cantidadRatings(){
              return ratings.size();
       }

       public int cantidadRatingsGenero(Genero genero, MyHash<Integer, Pelicula> peliculas){
              int cantidad = 0;
              for (int i = 0; i<ratings.size(); i++){
                     Rating rating = ratings.get(i);
                     if (peliculas.get(rating.getIdPelciula()).getGeneros().contains(genero)){
                            cantidad++;
                     }
              }
              return cantidad;
       }
}

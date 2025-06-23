package um.edu.uy;

import com.opencsv.exceptions.CsvValidationException;
import um.edu.uy.entities.UMovie;
import um.edu.uy.exceptions.InvalidIndex;
import um.edu.uy.exceptions.ValueNoExists;

import java.io.IOException;


//Aca hago el menu
public class Main {
    public static void main(String[] args) throws CsvValidationException, InvalidIndex, ValueNoExists, IOException {
        UMovie app = new UMovie();//Defino una variable que llame a mi clase UmMovie
        app.pruebaCargaPelis();
    }
}

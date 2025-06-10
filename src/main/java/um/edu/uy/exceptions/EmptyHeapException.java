package um.edu.uy.exceptions;

public class EmptyHeapException extends Exception{
    public EmptyHeapException(String mensaje ){
        System.out.println(mensaje);
    }
}

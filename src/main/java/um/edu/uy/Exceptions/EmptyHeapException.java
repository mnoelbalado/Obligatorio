package um.edu.uy.Exceptions;

public class EmptyHeapException extends Exception{
    public EmptyHeapException(String mensaje ){
        System.out.println(mensaje);
    }
}

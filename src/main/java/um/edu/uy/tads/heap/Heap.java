package um.edu.uy.tads.heap;
import um.edu.uy.exceptions.*;

//ES UN MIN HEAP
public interface Heap<T extends Comparable<T>> {

    void insert(T element); //inserta un nuevo elemento en el heap

    T peek() throws HeapEmptyException; //devuelve el elemento mínimo sin eliminarlo, lanza HeapEmptyException si el heap está vacío.

    T removeMin() throws HeapEmptyException; //elimina y devuelve el elemento mínimo, lanza HeapEmptyException si el heap está vacío.

    boolean isEmpty(); //devuelve true si el heap esta vacio

    int size(); //devuelve la cant de elementos del heap

    void clear(); //elimina todos los elementos del heap
}

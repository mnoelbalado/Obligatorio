package um.edu.uy.tads.linkedList;
import um.edu.uy.exceptions.*;

public interface LinkedList<T> {

    void add(T element); //agrega un elemento AL FINAL de la lista

    void addFirst(T element); //agrega un elemento AL PRINCIPIO de la lista

    void remove(T element) throws ElementNotFound; //elimina la primera aparición del elemento en la lista, lanza ElementNotFound si el elemento no está.

    T get(int index) throws InvalidIndex; //devuelve el elemento en la posición indicada, lanza InvalidIndex si el índice está fuera de rango.

    boolean contains(T element); //devuelve true si el elemento está en la lista, false si no.

    int size(); //devuelve la cantidad de elementos en la lista.

    boolean isEmpty(); //devuelve true si la lista está vacía, false si no lo esta.

    void clear(); //elimina todos los elementos de la lista
}

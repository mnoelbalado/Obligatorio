package um.edu.uy.tads.linkedList;

public interface LinkedList <T> {
    void add(T element); //agrega elemento al final de la lista
    void addFirst(T element); //agrega elemento al ppio de la lista
    boolean remove(T element); // elimina (la primera aparicion) de un elemento en la lista. devuelve true o false.
    T get(int index); //devuelve el elemento que está en la posición 'index'
    boolean contains(T element); //devuelve true si un elemento esta ne la lista, false si no
    int size(); //devuelve la cant de elem de la lista
    boolean isEmpty(); //devuelve true si esta vacia, false si no lo esta
    void clear(); //elimina todos los elem de la lista
}

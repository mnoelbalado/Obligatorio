package um.edu.uy.tads.hashTable;
import um.edu.uy.exceptions.*;
import um.edu.uy.tads.linkedList.MyLinkedList;

public interface HashTable<K, V> {
    void put(K key, V value) throws ElementAlreadyExists; //inserta un nuevo par clave_valor, y lanza excepcion ElementAlreadyExists si la clave ya existe en la tabla

    V get(K key) throws ValueNoExists; //devuelve el valor asociado a una clave, lanza valueNoExists si la clave no esta en la tabla

    boolean remove(K key) throws ValueNoExists; //elimina la entrada asociada a una clave, y lanza ValueNoExists si la clave no esta en la tabla

    boolean containsKey(K key); //verifica si la tabla contiene una clave especifica

    int size(); //devuelve la cantidad de elementos almacenados

    boolean isEmpty(); //verifica si la tabla esta vacia

    MyLinkedList<K> keys(); // devuelve todas las claves almacenadas
}

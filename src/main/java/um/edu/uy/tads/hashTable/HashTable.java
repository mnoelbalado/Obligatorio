package um.edu.uy.tads.hashTable;

public interface HashTable <K, V> {
    void put(K key, V value); //inserta o actualiza un par clave-valor
    V get(K key); //devuelve el valor asociado a la clave
    boolean remove(K key); //elimina el par clave-valor
    boolean containsKey(K key); //verifica si una clave existe
    int size(); //devuelve la cantidad de elementos
    boolean isEmpty(); //verifica si la tabla está vacía
}

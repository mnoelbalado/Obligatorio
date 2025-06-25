package um.edu.uy.TADS.HashTable;
import um.edu.uy.Exceptions.ValueNoExists;
import um.edu.uy.TADS.LinkedList.MyLinkedList;


public interface MyHash<K extends Comparable<K>,V > {

    public void put(K key, V value);

    public V get(K key);

    public boolean contains(K key);

    public void remove(K clave);

    public int capacity();

    public int size();

    public MyLinkedList<K> getKeys();

}

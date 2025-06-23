package um.edu.uy.tads.hashTable;


import um.edu.uy.exceptions.ValueNoExists;
import um.edu.uy.tads.linkedList.MyLinkedList;

public interface MyHash<K extends Comparable<K>,V extends Comparable<V>> {

    public void put(K key, V value)
            ;
    public V get(K key) throws ValueNoExists;

    public boolean contains(K key);

    public void remove(K clave);

    public int capacity();

    public int size();

    public MyLinkedList<NodoHash<K, V>> getNodesAsList(boolean reversed);

    public MyLinkedList<NodoHash<V, K>> getNodesAsSwappedList(boolean reversed);

    String printOnScreen(boolean fully);

}

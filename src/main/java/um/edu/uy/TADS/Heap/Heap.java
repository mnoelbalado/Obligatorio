package um.edu.uy.TADS.Heap;


import um.edu.uy.Exceptions.EmptyHeapException;

public interface Heap<K extends Comparable<K>, T> {

    void put(K key, T value);

    T delete() throws EmptyHeapException;

    int size();

    boolean isEmpty();

    HeapNode<K, T> getNode();

    T getValue();

    K getKey();

    boolean containsKey(K key) throws EmptyHeapException;

}


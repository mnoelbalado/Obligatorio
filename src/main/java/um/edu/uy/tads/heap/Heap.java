package um.edu.uy.tads.heap;


import um.edu.uy.exceptions.EmptyHeapException;

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


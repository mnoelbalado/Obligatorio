package um.edu.uy.tads.heap;

public class HeapNode<K extends Comparable<K>, T> {
    private K key;
    private T value;

    public HeapNode(K key, T value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

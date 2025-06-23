package um.edu.uy.tads.heap;
import um.edu.uy.exceptions.EmptyHeapException;


public class MyHeap<K extends Comparable<K>, T> implements Heap<K, T> {
    private int size;
    private HeapNode<K, T>[] heap;
    private boolean isMin;

    public MyHeap(boolean isMin) {
        this.isMin = isMin;
        this.size = 0;
        this.heap = new HeapNode[10]; // Initial size can be adjusted as needed
    }

    public MyHeap(boolean isMin, K[] array) {
        this.isMin = isMin;
        this.size = array.length;
        this.heap = new HeapNode[array.length];

        // Se insertan los elementos del heap al array
        for (int i = 0; i < array.length; i++) {
            heap[i] = new HeapNode<>(array[i], null);
        }

        // Ordenar Heap
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    public void put(K key, T value) {
        if (size >= heap.length - 1) {
            resize();
        }

        HeapNode<K, T> nodoNuevo = new HeapNode<>(key, value);
        heap[size] = nodoNuevo;
        size++;

        // Vuelve a ordenar
        int posicion = size - 1;
        while (posicion > 0) {
            int posicionPadre = (posicion - 1) / 2;
            boolean comp;
            if (this.isMin){
                comp = compare(heap[posicion].getKey(), heap[posicionPadre].getKey()) < 0;
            }
            else{
                comp = compare(heap[posicion].getKey(), heap[posicionPadre].getKey()) > 0;
            }
            if (comp) {
                // Va a estar siempre intercambiando posicion con el padre hasta que termine el while
                swap(posicion, posicionPadre);
                posicion = posicionPadre;
            } else {
                break;
            }
        }
    }

    /** Borra la raiz, si es maxheap el mas grande, si es minheap el mas chico **/
    public T delete() throws EmptyHeapException {
        if (isEmpty()) {
            throw new EmptyHeapException("Heap esta vacio");
        }

        T rootValue = heap[0].getValue();
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        // Si no se termino de borrar lo ordena
        if (size > 0) {
            heapify(0);
        }

        return rootValue;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void heapify(int index) {
        int hijoIz = 2 * index + 1;
        int hijoDer = 2 * index + 2;
        int extremo = index; // Mayor o menor depende de isMin

        if (isMin) { // min heap
            if (hijoIz < size && compare(heap[hijoIz].getKey(), heap[extremo].getKey()) < 0) {
                extremo = hijoIz;
            }
            if (hijoDer < size && compare(heap[hijoDer].getKey(), heap[extremo].getKey()) < 0) {
                extremo = hijoDer;
            }
        } else { // max heap
            if (hijoIz < size && compare(heap[hijoIz].getKey(), heap[extremo].getKey()) > 0) {
                extremo = hijoIz;
            }
            if (hijoDer < size && compare(heap[hijoDer].getKey(), heap[extremo].getKey()) > 0) {
                extremo = hijoDer;
            }
        }

        if (extremo != index) {
            swap(index, extremo);
            heapify(extremo);
        }
    }

    private void swap(int i, int j) {
        HeapNode<K, T> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void resize() {
        int newCapacity = heap.length * 2; //Dobla el largo
        HeapNode<K, T>[] newHeap = new HeapNode[newCapacity];
        for (int i = 0; i < heap.length; i++) { // Guarda todos los valores
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    private int compare(K key1, K key2) {
        // Compare based on whether it's a min-heap or max-heap
        if (isMin) {
            return key1.compareTo(key2); // Si es heap minimo
        } else {
            return key2.compareTo(key1); // Si es heap maximo
        }
    }

    @Override
    public HeapNode<K, T> getNode(){
        return heap[0];
    }

    @Override
    public T getValue(){
        return heap[0].getValue();
    }

    @Override
    public K getKey(){
        return heap[0].getKey();
    }

    @Override
    public boolean containsKey(K key) throws EmptyHeapException {
        if (isEmpty()) {
            throw new EmptyHeapException("Heap esta vacio");
        }
    return false;
    }


}

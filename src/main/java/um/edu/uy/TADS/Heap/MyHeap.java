package um.edu.uy.TADS.Heap;

import um.edu.uy.Exceptions.EmptyHeapException;

public class MyHeap<K extends Comparable<K>, T> implements Heap<K, T> {
    private int size;
    private HeapNode<K, T>[] heap;
    private boolean isMin;

    public MyHeap(boolean isMin) {
        this.isMin = isMin;
        this.size = 0;
        this.heap = new HeapNode[10]; // Initial size can be adjusted as needed
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
            if (compare(heap[posicion].getKey(), heap[posicionPadre].getKey()) < 0) {
                swap(posicion, posicionPadre);
                posicion = posicionPadre;
            } else {
                break;
            }
        }
    }

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
        int Extremo = index; // Mayor o menor depende de isMin

        if (hijoIz < size && compare(heap[hijoIz].getKey(), heap[Extremo].getKey()) < 0) {
            Extremo = hijoIz;
        }

        if (hijoDer < size && compare(heap[hijoDer].getKey(), heap[Extremo].getKey()) < 0) {
            Extremo = hijoDer;
        }

        if (Extremo != index) {
            swap(index, Extremo);
            heapify(Extremo);
        }
    }

    private void swap(int index1, int index2) {
        HeapNode<K, T> temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private void resize() {
        int newCapacity = heap.length * 2; //Dobla el largo
        HeapNode<K, T>[] newHeap = new HeapNode[newCapacity];
        for (int nodoIndex = 0; nodoIndex < heap.length; nodoIndex++) { // Guarda todos los valores
            newHeap[nodoIndex] = heap[nodoIndex];
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

}

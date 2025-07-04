package um.edu.uy.TADS.LinkedList;
import um.edu.uy.Exceptions.*;

public class MyLinkedList<T> implements LinkedList<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    @Override
    public void add(T element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
        size++;
    }

    @Override
    public void addFirst(T element) {
        Node newNode = new Node(element);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public void remove(T element) throws ElementNotFound {
        if (head == null) {
            throw new ElementNotFound("La lista está vacía. No se puede eliminar.");
        }

        if (head.data.equals(element)) {
            head = head.next;
            size--;
            return;
        }

        Node current = head;
        while (current.next != null && !current.next.data.equals(element)) {
            current = current.next;
        }

        if (current.next == null) {
            throw new ElementNotFound("Elemento no encontrado en la lista.");
        }

        current.next = current.next.next;
        size--;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Índice fuera de rango: " + index);
            return null;
        }

        Node current = head;
        for (int nodoIndex = 0; nodoIndex < index; nodoIndex++)
            current = current.next;

        return current.data;
    }

    @Override
    public boolean contains(T element) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(element)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public MyLinkedList<T> getValues() {
        MyLinkedList<T> values = new MyLinkedList<>();

        Node current = this.head; // Cambiar 'first' por 'head'
        while (current != null) {
            values.add(current.data); // Cambiar 'getData()' por 'data'
            current = current.next; // Cambiar 'getNext()' por 'next'
        }

        return values; // Agregar return que faltaba

    }


}
package um.edu.uy.tads.hashTable;

import um.edu.uy.tads.linkedList.MyLinkedList;
import um.edu.uy.exceptions.*;

public class MyHashTable<K, V> implements HashTable<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private MyLinkedList<Entry<K, V>>[] buckets; //array de listas para colisiones
    private int capacity;  //cantidad de "buckets"
    private int size;      //total de elementos insertados

    // Java no permite crear arreglos de tipos genéricos directamente.
    // Por eso usamos un arreglo de MyLinkedList sin tipo específico.
    // Este cast es seguro porque controlamos que siempre se guarden listas válidas.
    //POR ESO PONEMOS EL SUPPRESS WARNING que "silencia" esta advertencia
    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        this.capacity = capacity;
        buckets = new MyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new MyLinkedList<>();
        }
    }

    //función hash básica
    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    @Override
    public void put(K key, V value) throws ElementAlreadyExists {
        int index = hash(key);
        MyLinkedList<Entry<K, V>> bucket = buckets[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = null;
            try {
                entry = bucket.get(i);
            } catch (InvalidIndex e) {
                throw new RuntimeException(e);
            }
            if (entry.key.equals(key)) {
                //si la clave ya existe, se lanza excepción
                throw new ElementAlreadyExists("La clave '" + key + "' ya existe en la tabla.");
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;
    }

    @Override
    public V get(K key) throws ValueNoExists {
        int index = hash(key);
        MyLinkedList<Entry<K, V>> bucket = buckets[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = null;
            try {
                entry = bucket.get(i);
            } catch (InvalidIndex e) {
                throw new RuntimeException(e);
            }
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        throw new ValueNoExists("La clave '" + key + "' no existe en la tabla.");
    }

    @Override
    public boolean remove(K key) throws ValueNoExists {
        int index = hash(key);
        MyLinkedList<Entry<K, V>> bucket = buckets[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = null;
            try {
                entry = bucket.get(i);
            } catch (InvalidIndex e) {
                throw new RuntimeException(e);
            }
            if (entry.key.equals(key)) {
                try {
                    bucket.remove(entry);
                } catch (ElementNotFound e) {
                    throw new RuntimeException(e);
                }
                size--;
                return true;
            }
        }

        throw new ValueNoExists("No se puede eliminar: la clave '" + key + "' no existe.");
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        MyLinkedList<Entry<K, V>> bucket = buckets[index];

        for (int i = 0; i < bucket.size(); i++) {
            try {
                if (bucket.get(i).key.equals(key)) {
                    return true;
                }
            } catch (InvalidIndex e) {
                throw new RuntimeException(e);
            }
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
}

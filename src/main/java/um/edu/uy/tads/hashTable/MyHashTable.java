package um.edu.uy.tads.hashTable;

import um.edu.uy.tads.linkedList.MyLinkedList;

public class MyHashTable <K, V> implements HashTable<K, V> {
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

    @SuppressWarnings("unchecked") //
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
    public void put(K key, V value) {
        int index = hash(key);
        MyLinkedList<Entry<K, V>> bucket = buckets[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.key.equals(key)) {
                entry.value = value; //si la clave ya existe, se actualiza el valor
                return;
            }
        }

        bucket.add(new Entry<>(key, value)); //si no existe, se agrega una nueva entrada
        size++;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        MyLinkedList<Entry<K, V>> bucket = buckets[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null; //no se encontró la clave
    }

    @Override
    public boolean remove(K key) {
        int index = hash(key);
        MyLinkedList<Entry<K, V>> bucket = buckets[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.key.equals(key)) {
                bucket.remove(entry); //elimina la entrada si la clave coincide
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
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

package um.edu.uy.TADS.HashTable;
import um.edu.uy.Exceptions.ValueNoExists;
import um.edu.uy.TADS.LinkedList.LinkedList;
import um.edu.uy.TADS.LinkedList.MyLinkedList;


import java.lang.Math;

import static java.lang.Math.round;

public class MyHashTable<K extends Comparable<K>, V > implements MyHash<K, V> {
    private NodoHash[] tablahash;
    private int size;
    // Indica que tan llena debe de estar la tabla para redimensionarse, debe de ser un valor decimal menor a 1
    private double LoadFactor = 0.75f;

    public int capacity;

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        this.tablahash = new NodoHash[this.capacity];
        this.size = 0;
    }

    private void resize() {
        NodoHash<K, V>[] oldTable = tablahash;
        capacity = 2 * capacity;
        size = 0;
        tablahash = new NodoHash[capacity];
        for (NodoHash<K, V> nodoTemp : oldTable) {
            if(nodoTemp != null ){
                // Ya cambia el size
                this.put(nodoTemp.getKey(), nodoTemp.getValue());
            }
        }
    }

    @Override
    public void put(K key, V value) {
        int pos = HashFunction(key);
        NodoHash<K, V> nodoAgregado = new NodoHash<>(key, value);
        if (tablahash[pos] == null) {
            tablahash[pos] = nodoAgregado;
        } else {
            while (tablahash[pos] != null) {
                if (tablahash[pos].getKey().equals(key)) {
                    tablahash[pos] = nodoAgregado;
                    return;
                }
                if(pos == capacity-1){
                    pos=0;
                }
                pos = pos + 1;
            }
            tablahash[pos] = nodoAgregado;
        }
        size ++;

        // Cada vez que agregas un elemento, el programa debe de fijarse
        //
        if((size) >= (int)(capacity * LoadFactor)) {
            resize();
        }

    }


    private NodoHash<K, V> getNodo(K key) {
        int pos = HashFunction(key);
        while (tablahash[pos] != null) {
            if (tablahash[pos].getKey().equals(key)) {
            return tablahash[pos];
            } else {
                if(pos == capacity-1){
                    return null;
                }
                pos = pos + 1;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        NodoHash<K, V> nodo = getNodo(key);
        if(nodo != null) {
            return nodo.getValue();
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        int pos = HashFunction(key);
        while (pos < capacity && tablahash[pos] != null){
            if(tablahash[pos].getKey().equals(key)){
                return true;
            }
            pos =pos +1;
        }
        return false;
    }

    @Override
    public void remove(K clave) {
        int pos = HashFunction(clave);
        while(tablahash[pos]!= null) {
            if (tablahash[pos].getKey().equals(clave)) {
                tablahash[pos] = null;
            }
            pos = (pos + 1)%capacity;
        }
        size --;
    }

    /** Devuelve la posicion **/
    public int HashFunction(K key){
        int HashValue = key.hashCode(); // Cada elemento K tiene una key asociada (por comparable)
        return Math.abs(HashValue) % capacity;
    }

    /** Prints the hashmap on screen **/
    public String printOnScreen(boolean fully){
        String resp = "{";
        for (int i = 0; i<this.capacity; i++){
            boolean hasElement = false;
            if (this.tablahash[i] != null) {
                resp += this.tablahash[i].getKey() + ":" + this.tablahash[i].getValue();
                hasElement = true;
            }
            else {
                if (fully){
                    resp += "null:null";
                    hasElement = true;
                }
            }
            if (i != this.capacity-1 && hasElement) {
                resp += ", ";
            }
        }
        resp += "}";
        return resp;
    }

    @Override
    public String toString(){return this.printOnScreen(false);}

    public int capacity(){
        return this.capacity;
    }

    public int size(){
        return this.size;
    }



    public MyLinkedList<K> getKeys(){
        MyLinkedList<K> temp = new MyLinkedList<>();
        for (int index = 0; index<this.capacity; index++){
            if (this.tablahash[index] != null){
                temp.add((K) this.tablahash[index].getKey());
            }
        }
        return temp;
    }

    @Override
    public LinkedList<V> getValues() {
        MyLinkedList<V> values = new MyLinkedList<>();

        for (int index = 0; index < this.capacity; index++) {
            if (this.tablahash[index] != null) {
                values.add((V) this.tablahash[index].getValue());
            }
        }

        return values;
    }


}

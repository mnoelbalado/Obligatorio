package um.edu.uy.tads.heap;
import um.edu.uy.exceptions.*;


public class MyHeap<T extends Comparable<T>> implements Heap<T> {

    private T[] arreglo; //aca se guarda el heap
    private int cantidad; //cant actual de elementos
    private static final int TAMANIO_INICIAL = 10; //tamaño inicial del arreglo del heap; se expande si se llena

    @SuppressWarnings("unchecked")
    public MyHeap() {
        arreglo = (T[]) new Comparable[TAMANIO_INICIAL];
        cantidad = 0;
    }

    @Override
    public void insert(T elemento) {
        if (cantidad == arreglo.length) {
            agrandar(); //si no entra más, agrando el arreglo
        }
        arreglo[cantidad] = elemento; //lo ponemos al final
        moverHaciaArriba(cantidad);  //lo subimos hasta su lugar correcto
        cantidad++;
    }

    @Override
    public T peek() throws HeapEmptyException {
        if (isEmpty()) {
            throw new HeapEmptyException("El heap está vacío, no hay mínimo.");
        }
        return arreglo[0]; //el mínimo siempre está en la posición 0 pq es un MIN HEAP
    }

    @Override
    public T removeMin() throws HeapEmptyException {
        if (isEmpty()) {
            throw new HeapEmptyException("El heap está vacío, no se puede eliminar.");
        }

        T minimo = arreglo[0]; //guardamos el mínimo
        arreglo[0] = arreglo[cantidad - 1]; //ponemos el último en la raíz
        cantidad--;
        moverHaciaAbajo(0); //lo bajamos hasta su lugar correcto
        return minimo;
    }

    @Override
    public boolean isEmpty() {
        return cantidad == 0;
    }

    @Override
    public int size() {
        return cantidad;
    }

    @Override
    public void clear() {
        cantidad = 0;
    }

    //metodo q sube un elem si es mas chico q su padre
    private void moverHaciaArriba(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (arreglo[i].compareTo(arreglo[padre]) < 0) {
                intercambiar(i, padre);
                i = padre;
            } else {
                break;
            }
        }
    }

    //metodo q baja un elemento si es mas grade q sus hijos
    private void moverHaciaAbajo(int i) {
        while (i * 2 + 1 < cantidad) {
            int hijoIzq = i * 2 + 1;
            int hijoDer = i * 2 + 2;
            int menor = hijoIzq;

            if (hijoDer < cantidad && arreglo[hijoDer].compareTo(arreglo[hijoIzq]) < 0) {
                menor = hijoDer;
            }

            if (arreglo[i].compareTo(arreglo[menor]) > 0) {
                intercambiar(i, menor);
                i = menor;
            } else {
                break;
            }
        }
    }

    //metodo q duplica el tamaño del arreglo si esta lleno
    @SuppressWarnings("unchecked")
    private void agrandar() {
        T[] nuevo = (T[]) new Comparable[arreglo.length * 2];
        for (int i = 0; i < arreglo.length; i++) {
            nuevo[i] = arreglo[i];
        }
        arreglo = nuevo;
    }

    //intercambia dos posiciones dle arreglo
    private void intercambiar(int i, int j) {
        T aux = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = aux;
    }
}

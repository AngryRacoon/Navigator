package org.example;
import org.example.Node;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public LinkedList(){
        this.head = null;
        this.size = 0;
    }

    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if(head == null){
            head = newNode;
        }else{
            Node<T> current = head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    public T get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for(int i = 0; i < index; i++){
            current = current.getNext();
        }
        return current.getData();
    }

    public void remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0){
            head = head.getNext();
        }else{
            Node<T> current = head;
            for(int i = 0; i < index - 1; i++){
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
        }
        size--;
    }


    public T getByElement(T element) {
        	Node<T> current = head;
        	while(current != null) {
        		if(current.getData().equals(element)) {
        			return current.getData();
        		}
        		current = current.getNext();
        	}
        	return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }

    public void sort(Comparator<T> comparator) {
        if (size > 1) {
            for (int i = 0; i < size; i++ ) {
                Node<T> current = head;
                Node<T> next = head.getNext();
                for (int j = 0; j < size - 1; j++) {
                    if (comparator.compare(current.getData(), next.getData()) > 0) {
                        T temp = current.getData();
                        current.setData(next.getData());
                        next.setData(temp);
                    }
                    current = next;
                    next = next.getNext();
                }
            }
        }
    }
    public int size(){
        return size;
    }

}
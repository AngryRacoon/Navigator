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

    private Node<T> getMiddle(Node<T> head) {
        if (head == null) {
            return head;
        }
        Node<T> slow = head, fast = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    private Node<T> mergeSort(Node<T> head, Comparator<T> comparator) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        Node<T> middle = getMiddle(head);
        Node<T> nextOfMiddle = middle.getNext();
        middle.setNext(null);
        Node<T> left = mergeSort(head, comparator);
        Node<T> right = mergeSort(nextOfMiddle, comparator);
        Node<T> sortedList = merge(left, right, comparator);
        return sortedList;
    }

    private Node<T> merge(Node<T> a, Node<T> b, Comparator<T> comparator) {
        Node<T> result = null;
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (comparator.compare(a.getData(), b.getData()) <= 0) {
            result = a;
            result.setNext(merge(a.getNext(), b, comparator));
        } else {
            result = b;
            result.setNext(merge(a, b.getNext(), comparator));
        }
        return result;
    }

    public void sort(Comparator<T> comparator) {
        head = mergeSort(head, comparator);
    }

    public int size(){
        return size;
    }

}
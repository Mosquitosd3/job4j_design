package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinked<E> implements Iterable<E> {

    private Node<E> first;
    private Node<E> lust;
    private int size = 0;
    private int modCount = 0;

    static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }

    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (size == 0) {
            first = newNode;
            lust = newNode;
        } else {
            lust.next = newNode;
            lust = newNode;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return (E) current.value;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E>() {
            Node<E> node = first;
           int expectedModCount = modCount;
           int point = 0;
           @Override
           public boolean hasNext() {
               return point < size;
           }

           @Override
           public E next() {
               if (expectedModCount != modCount) {
                   throw new ConcurrentModificationException();
               }
               if (!hasNext()) {
                   throw new NoSuchElementException();
               }
             E rsl = first.value;
               first = first.next;
               point++;
               return rsl;
           }
       };
       return iterator;
    }
}

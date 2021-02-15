package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private int size = 0;
    private int modCount = 0;
    private Object[] list = new Object[10];

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) list[index];
    }

    public void add(T model) {
        if (size == list.length) {
            Object[] temp = new Object[list.length * 2];
            System.arraycopy(list, 0, temp, 0, size);
            list = temp;
        }
        list[size] = model;
        size++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator iterator = new Iterator() {

            int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return list[point++];

            }

        };
        return iterator;
    }
}
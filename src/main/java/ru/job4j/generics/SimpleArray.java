package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private int size = 0;
    private Object[] data;

    /**
     * this constructor create size array
     * @throws IllegalArgumentException  if capacity <= 0
     * @param capacity
     */
    public SimpleArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        data = new Object[capacity];
    }

    /**
     *adds model to the array
     * @param model to add
     */
    public void add(T model) {
        data[size] = model;
        size++;
    }

    /**replace model for index
     *
     * @param index
     * @param model
     */
    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        data[index] = model;
    }

    /**
     * delete element by index
     * @param index
     */
    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
    }

    /**
     *
     * @param index
     * @return element T data[] by index
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) data[index];
    }


    @Override
    public Iterator<T> iterator() {
        Iterator iterator = new Iterator() {
            int point = 0;
            @Override
            public boolean hasNext() {
                return point < data.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[point++];
            }
        };
        return iterator;
    }
}



package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {
    private SimpleArray simpleSet;

    public SimpleSet() {
        simpleSet = new SimpleArray();
    }

    public boolean add(E element) {
        if (!contains(element)) {
            simpleSet.add(element);
            return true;
        }
        return false;
    }

    public boolean contains(E element) {
        for (int index = 0; index < simpleSet.getSize(); index++) {
            if (Objects.equals(simpleSet.get(index), element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return simpleSet.iterator();
    }
}

package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = findIndex(id);
        if (index != -1) {
           mem.set(index, model);
           return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int index = findIndex(id);
        if (index != -1) {
            mem.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        return mem.stream().filter(el -> el.getId().equals(id)).findFirst().orElse(null);
    }

    private int findIndex(String id) {
        int index = 0;
        for (T el: mem) {
            if (el.getId().equals(id)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}

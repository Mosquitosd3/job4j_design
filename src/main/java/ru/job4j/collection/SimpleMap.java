package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<V> {
    private Node[] hashTable;
    private int capacity = 16;
    private double loadFactor = 0.75;
    private int size = 0;
    private int modCount = 0;

    public SimpleMap() {
        hashTable = new Node[capacity];
    }

    public boolean insert(K key, V value) {
        if (size == loadFactor * capacity) {
            resizeHashTable();
        }
        int index = hash(key);
        if (hashTable[index] != null) {
            if (hashTable[index].getKey().hashCode() != key.hashCode()) {
                return false;
            } else if (hashTable[index].getKey().equals(key)) {
                hashTable[index].setValue(value);
                return true;
            }
        }
        Node node = new Node(key, value);
        hashTable[index] = node;
        size++;
        modCount++;
        return true;
     }

    public V get(K key) {
        int index = hash(key);
        if (hashTable[index] != null) {
            if (hashTable[index].getKey().hashCode() == key.hashCode() && hashTable[index].getKey().equals(key)) {
                return (V) hashTable[index].getValue();
            }
        }
        return null;
    }

    public boolean delete(K key) {
        int index = hash(key);
        if (hashTable != null) {
            if (hashTable[index].getKey().hashCode() == key.hashCode() && hashTable[index].getKey().equals(key)) {
                hashTable[index] = null;
                size--;
                modCount--;
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    private void resizeHashTable() {
        capacity *= 2;
        Node[] temp = new Node[capacity];
        for (int count = 0; count < hashTable.length; count++) {
            if (hashTable[count] != null) {
                int index = hash((K) hashTable[count].getKey());
                temp[index] = hashTable[count];
            }
        }
        hashTable = temp;
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % capacity - 1);
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                while (point < hashTable.length && hashTable[point] == null) {
                    point++;
                }
                return point < hashTable.length;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (V) hashTable[point].getValue();
            }
        };
    }

    private class Node<K, V> {
         K key;
         V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
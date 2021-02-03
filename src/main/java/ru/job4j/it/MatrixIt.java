package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int size = 0;
        if (row < data.length) {
            for (int[] el : data) {
                size += el.length;
            }
            if (size != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (row < data.length && column >= data[row].length) {
            column = 0;
            row++;
        }
        return data[row][column++];
    }
}

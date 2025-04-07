package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
        this.row = 0;
        this.column = 0;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && (data[row] == null || column >= data[row].length)) {
            row++;
            column = 0;
        }
        return row < data.length && column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int value = data[row][column];
        if (column < data[row].length - 1) {
            column++;
        } else {
            column = 0;
            row++;
        }
        return value;
    }
}
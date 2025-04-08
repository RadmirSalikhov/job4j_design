package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index;

    public NonNullIterator(Integer[] data) {
        this.data = data;
        this.index = 0;
    }

    /**
     * Находим следующее не null значение в массиве.
     *
     * @return {@code true}, если найдено не null значение, {@code false} если все чётные числа были перебраны.
     */
    @Override
    public boolean hasNext() {
        while (index < data.length && data[index] == null) {
            index++;
        }
        return index < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
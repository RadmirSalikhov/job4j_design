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

    @Override
    public boolean hasNext() {
        return findNextNotNullEven();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    /**
     * Находит следующее не null значение в массиве.
     *
     * @return {@code true}, если найдено не null значение, {@code false} если все чётные числа были перебраны.
     */
    private boolean findNextNotNullEven() {
        while (index < data.length && data[index] == null) {
            index++;
        }
        return index < data.length;
    }
}
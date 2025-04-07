package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        this.index = 0;  // Начинаем с первого элемента массива
    }

    @Override
    public boolean hasNext() {
        return findNextEven();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    /**
     * Находит следующее чётное число в массиве.
     *
     * @return {@code true}, если найдено чётное число, {@code false} если все чётные числа были перебраны.
     */
    private boolean findNextEven() {
        // Пропускаем элементы, пока не найдем чётное число
        while (index < data.length && data[index] % 2 != 0) {
            index++;
        }
        return index < data.length;
    }
}
package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    private int index = 0;

    public CyclicIterator(List<T> data) {
        this.data = data != null ? data : List.of();
    }

    @Override
    public boolean hasNext() {
        return !data.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No elements in the collection");
        }
        T value = data.get(index);
        index = (index + 1) % data.size();
        return value;
    }
}
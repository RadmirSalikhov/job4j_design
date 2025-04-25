package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    private void grow() {
        int newCapacity = container.length == 0 ? 1 : container.length * 2;
        container = Arrays.copyOf(container, newCapacity);
    }

    private void checkIndex(int index) {
        Objects.checkIndex(index, size);
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        checkIndex(index);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[--size] = null;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            private final int expectedModCount = modCount;

            private void checkForModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkForModification();
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}
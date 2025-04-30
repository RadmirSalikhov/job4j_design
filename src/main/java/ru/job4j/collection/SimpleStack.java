package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public void peek() {
        Iterator<T> it = linked.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        it.next();
    }
}

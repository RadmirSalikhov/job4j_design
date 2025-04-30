package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    public T poll() {
        if (outputIsEmpty()) {
            transferInputToOutput();
        }
        if (outputIsEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
    }

    private boolean outputIsEmpty() {
        try {
            output.peek();
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    private void transferInputToOutput() {
        while (true) {
            try {
                output.push(input.pop());
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }
}

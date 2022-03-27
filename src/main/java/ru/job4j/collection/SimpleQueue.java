package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inCount = 0;
    private int outCount = 0;

    public T poll() {
        if (inCount == 0 && outCount == 0) {
            throw new NoSuchElementException();
        }
        if (outCount == 0) {
            outCount = inCount - 1;
            while (inCount > 0) {
                out.push(in.pop());
                inCount--;
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inCount++;
    }
}

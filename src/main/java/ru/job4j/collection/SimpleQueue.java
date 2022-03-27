package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inCount = 0;
    private int outCount = 0;

    public T poll() {
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

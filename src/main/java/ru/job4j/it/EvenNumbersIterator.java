package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public int nextEvenIndex() {
        while (index < data.length && data[index] % 2 != 0) {
            index++;
        }
        return index;
    }

    @Override
    public boolean hasNext() {
        nextEvenIndex();
        return index < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        nextEvenIndex();
        return data[index++];
    }
}
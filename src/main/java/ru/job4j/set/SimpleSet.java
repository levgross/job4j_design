package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        boolean notContains = !contains(value);
        if (notContains) {
            set.add(value);
        }
        return notContains;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T t : set) {
            if (Objects.equals(t, value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

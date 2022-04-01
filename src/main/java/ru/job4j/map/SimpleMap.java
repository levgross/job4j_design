package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) (count / (capacity - 1)) >= LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        boolean isNull = Objects.equals(table[index], null);
        if (isNull) {
            table[index] = new MapEntry<>(key, value);
        }
        count++;
        modCount++;
        return isNull;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private void expand() {
            capacity *= 2;
            count = 0;
            MapEntry<K, V>[] oldTable = table;
            table = new MapEntry[capacity];
            for (MapEntry<K, V> i : oldTable) {
                if (i.key != null) {
                    put(i.key, i.value);
                }
            }
    }

    @Override
    public V get(K key) {
        V value = null;
        MapEntry<K, V> entry = table[indexFor(hash(key.hashCode()))];
        if (entry != null && entry.key.hashCode() == key.hashCode() && Objects.equals(entry.key, key)) {
            value = entry.value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        boolean rsl = false;
        if (table[index] != null
                && table[index].key.hashCode() == key.hashCode()
                && Objects.equals(table[index].key, key)) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            int point = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}

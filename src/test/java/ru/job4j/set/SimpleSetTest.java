package ru.job4j.set;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenContains() {
        Set<String> string = new SimpleSet<>();
        assertTrue(string.add("one"));
        assertFalse(string.add("one"));
        assertTrue(string.add("two"));
        assertTrue(string.contains("two"));
    }

    @Test
    public void whenNotContains() {
        Set<String> string = new SimpleSet<>();
        assertTrue(string.add("one"));
        assertTrue(string.add("two"));
        assertFalse(string.contains("three"));
    }
}

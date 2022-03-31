package ru.job4j.map;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.it.ArrayIt;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {
    private SimpleMap<String, String> map = new SimpleMap<>();

    @Before
    public void setUp() {
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
    }

    @Test
    public void whenPutThenGet() {
        Assert.assertTrue(map.put("4", "four"));
        Assert.assertEquals(map.get("4"), "four");
    }

    @Test
    public void whenPutExistedThenFalse() {
        Assert.assertFalse(map.put("1", "uno"));
        Assert.assertEquals(map.get("1"), "one");
    }

    @Test
    public void whenGetNotExistedThenNull() {
        Assert.assertNull(map.get("5"));
    }

    @Test
    public void whenRemoveThenGetNull() {
        Assert.assertTrue(map.remove("2"));
        Assert.assertNull(map.get("2"));
    }

    @Test
    public void whenRemoveNotExistedThenFalse() {
        Assert.assertFalse(map.remove("4"));
    }

    @Test
    public void whenMultiCallHasNextThenTrue() {
        Iterator<String> it = map.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(it.hasNext());
    }

    @Test
    public void whenReadSequence() {
        Iterator<String> it = map.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(it.next(), "1");
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(it.next(), "2");
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(it.next(), "3");
        Assert.assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        SimpleMap<String, String> map = new SimpleMap<>();
        Iterator<String> it = map.iterator();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModifiedIt() {
        Iterator<String> it = map.iterator();
        map.put("6", "six");
        it.next();
    }
}
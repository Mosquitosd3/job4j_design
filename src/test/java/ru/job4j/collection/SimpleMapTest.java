package ru.job4j.collection;

import org.junit.Ignore;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void whenAdd() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.insert("Kolya", 26));
    }

    @Test
    public void whenAddDuplicate() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("Kolya", 26);
        map.insert("Kolya", 26);
        assertThat(1, is(map.getSize()));
    }

    @Test
    public void whenResizeHashTable() {
        //default capacity = 16
        //loadFactor = 0.75
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        map.insert("d", 4);
        map.insert("e", 5);
        map.insert("f", 6);
        map.insert("g", 7);
        map.insert("h", 8);
        map.insert("i", 9);
        map.insert("j", 10);
        map.insert("k", 11);
        map.insert("l", 12);
        map.insert("m", 13);
        map.insert("n", 14);
        map.insert("o", 15);
        map.insert("p", 16);
        assertTrue(map.insert("q", 17));
        assertThat(17, is(map.getSize()));
    }

    @Test
    public void whenGetElement() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("kolya", 26);
        int expect = 26;
        int input = map.get("kolya");
        assertThat(expect, is(input));
        assertNull(map.get("b"));
    }

    @Test
    public void whenDelete() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("Kolya", 26);
        int expectedSize = 0;
        assertTrue(map.delete("Kolya"));
        assertNull(map.get("kolya"));
        assertThat(expectedSize, is(0));
    }


    @Test
    public void whenIt() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("Kolya", 26);
        int input = map.iterator().next();
        int expect = 26;
        assertThat(expect, is(input));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFalse() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.iterator().next();
    }

}
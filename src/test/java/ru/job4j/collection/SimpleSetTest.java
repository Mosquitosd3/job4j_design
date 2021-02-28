package ru.job4j.collection;

import org.junit.Test;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    @Test
    public void whenAdd() {
        SimpleSet<String> simpleSet = new SimpleSet();
        assertTrue(simpleSet.add("one"));
    }

    @Test
    public void whenAddDuplicate() {
        SimpleSet<String> simpleSet = new SimpleSet();
        simpleSet.add("one");
        simpleSet.add("two");
        assertFalse(simpleSet.add("one"));
        assertFalse(simpleSet.add("two"));
        assertTrue(simpleSet.add("three"));
    }

    @Test
    public void whenIterator() {
        SimpleSet<String> simpleSet = new SimpleSet();
        simpleSet.add("one");
        assertThat("one", is(simpleSet.iterator().next()));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.iterator().next();
    }


}
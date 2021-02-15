package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedTest {
    SimpleLinked<Integer> list;

    @Before
    public void setUp() {
        list = new SimpleLinked<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenGetElement() {
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }


    @Test(expected = NoSuchElementException.class)
    public void whenIterator() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinked<Integer> list = new SimpleLinked<>();
        list.iterator().next();
    }
}
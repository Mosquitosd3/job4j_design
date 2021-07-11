package ru.job4j.kiss;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.Comparator;
import java.util.List;


public class MaxMinTest {
    MaxMin maxMin = new MaxMin();
    List<Integer> list = List.of(12, 3, 10, 5, 77, 4, 13);

    Comparator<Integer> comMax = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };


    Comparator<Integer> comMin = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return (o1 < o2) ? 1 : (o1 == o2) ? 0 : -1;
        }
    };


    @Test
    public void max() {
        assertThat(maxMin.max(list, comMax), is(77));
    }

    @Test
    public void min() {
        assertThat(maxMin.max(list, comMin), is(3));
    }
}
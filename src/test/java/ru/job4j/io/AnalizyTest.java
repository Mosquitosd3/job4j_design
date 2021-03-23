package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Test
    public void whenUnavailable() throws IOException {
        String source = "./files/server.log";
        String target = "./files/serverUnavailable.txt";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
        StringBuilder builder = new StringBuilder();
        String expect = "10:58:01;10:59:0111:01:02;11:02:02";
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(builder::append);
        }
        assertThat(expect, is(builder.toString()));
    }
}
package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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

    @Test
    public void whenTemporaryFolder() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        Analizy analizy = new Analizy();
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)));
             BufferedReader in = new BufferedReader(new FileReader("./files/server.log"))) {
            in.lines().filter(str -> !str.isEmpty()).forEach(line -> out.println(line));
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:58:01;10:59:0111:01:02;11:02:02"));
    }
}
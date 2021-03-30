package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        for (String name : visitor.getFileInfo().keySet()) {
            System.out.println("Fail name: " + name + " Size: " + visitor.getFileInfo().get(name));
        }
    }
}

package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        for (FileProperty file : visitor.getFileInfo()) {
            System.out.println("File Name: " + file.getName() + " size: " + file.getSize());
        }
    }
}

package ru.job4j.io.finder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Searcher {
    public static List<Path> search(Path root, Predicate<Path> predicate) throws IOException {
        FindFiles findFiles = new FindFiles(predicate);
        Files.walkFileTree(root, findFiles);
        return findFiles.getList();
    }
}

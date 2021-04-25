package ru.job4j.io.finder;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FindFiles extends SimpleFileVisitor<Path> {
    private List<Path> list = new ArrayList<>();
    private Predicate<Path> condition;

    public FindFiles(Predicate condition) {
        this.condition = condition;
    }

    public List<Path> getList() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            list.add(file);
        }
        return super.visitFile(file, attrs);
    }
}

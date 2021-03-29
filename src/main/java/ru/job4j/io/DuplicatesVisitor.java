package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> fileInfo = new HashSet<>();

    public Set<FileProperty> getFileInfo() {
        return fileInfo;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toFile().isFile()) {
        fileInfo.add(new FileProperty(file.toFile().length(), file.toFile().getName()));
        }
        return super.visitFile(file, attrs);
    }
}

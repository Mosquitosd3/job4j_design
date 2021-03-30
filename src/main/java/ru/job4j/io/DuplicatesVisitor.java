package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> duplicate = new HashSet<>();
    private Map<String, Long> fileInfo  = new HashMap();

    public Map<String, Long> getFileInfo() {
        return fileInfo;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File toFile = file.toFile();
        if (toFile.isFile()) {
            if (!duplicate.add(new FileProperty(toFile.length(), toFile.getName()))) {
                fileInfo.put(toFile.getAbsolutePath(), toFile.length());
            }
        }
        return super.visitFile(file, attrs);
    }
}

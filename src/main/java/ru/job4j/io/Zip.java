package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            for (Path el : source) {
                zip.putNextEntry(new ZipEntry(el.toString()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(el.toString()))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param args
     * -d - directory - которую мы хотим архивировать
     * -e - exclude - исключить файлы *.class
     * -o - output - во что мы архивируем.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ArgsName arg = ArgsName.of(args);
        List<Path> paths = Search.search(Path.of(arg.get("d")), el -> !el.toFile().getName().endsWith(arg.get("e")));
        new Zip().packFiles(paths, Path.of(arg.get("o")));
    }
}

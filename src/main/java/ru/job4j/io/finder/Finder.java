package ru.job4j.io.finder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Finder {

    /**
     * @param args
     * -d - директория, в которой начинать поиск.
     * -n - имя файла, маска, либо регулярное выражение.
     * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
     * -o - результат записать в файл.
     */
    public static void main(String[] args) throws IOException {
        Arg arg = Arg.of(args);
        List<Path> list = Searcher.search(Path.of(arg.get("d")), type(arg.get("t"), arg.get("n")));
        new Finder().toFile(list, Path.of(arg.get("o")));
    }

    public void toFile(List<Path> list, Path path) {
        try (FileWriter writer = new FileWriter(path.toString())) {
            list.stream().map(el -> el.toFile().getAbsolutePath()).forEach(el -> {
                try {
                    writer.write(el);
                    writer.write(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Predicate<Path> type(String t, String key) {
        if (t.equals("mask")) {
            return path -> path.toFile().getName().endsWith(key);
        } else if (t.equals("name")) {
            return path -> path.toFile().getName().equals(key);
        } else {
            throw new IllegalArgumentException();
        }
    }

}

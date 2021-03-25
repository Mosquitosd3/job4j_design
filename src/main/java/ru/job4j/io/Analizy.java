package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private boolean mark = true;
    private List<String> result = new ArrayList<>();
    private int nextLine = 0;
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines().filter(str -> !str.isEmpty()).forEach(str -> {
                int index = str.indexOf(" ") + 1;
                if (mark && str.contains("400") || str.contains("500")) {
                    result.add(str.substring(index) + ";");
                    mark = false;
                }
                if (!mark && str.contains("200") || str.contains("300")) {
                    result.add(str.substring(index) + System.lineSeparator());
                    mark = true;
                }
                if (nextLine < result.size()) {
                    writeToFile(target, result.get(nextLine++));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String target, String stringDate) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target, true))) {
            out.print(stringDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

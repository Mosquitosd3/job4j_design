package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            List<String> line = reader.lines().filter(el -> !el.isEmpty()).collect(Collectors.toList());
            for (int index = 0; index < line.size(); index++) {
                String txt;
                if (line.get(index).contains("400") || line.get(index).contains("500")) {
                    txt = line.get(index).substring(line.get(index).indexOf(" ") + 1) + ";";
                    int nextIndex = index + 1;
                    if (line.get(nextIndex).contains("200") || line.get(nextIndex).contains("300")) {
                        txt += line.get(nextIndex).substring(line.get(nextIndex).indexOf(" ") + 1);
                    }
                    writeToFile(target, txt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String target, String stringDate) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target, true))) {
            out.printf("%s%n", stringDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

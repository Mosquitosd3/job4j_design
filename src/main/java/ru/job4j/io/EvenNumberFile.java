package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("./files/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String str : lines) {
                System.out.println(Integer.parseInt(str) % 2 == 0 ? "even" : "odd");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

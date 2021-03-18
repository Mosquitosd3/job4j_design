package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFileMultiple {

    public void inputInFile(int size) {
        int[][] table = multiple(size);
        try (FileOutputStream out = new FileOutputStream("./files/multiple.txt")) {
            for (int[] row : table) {
                for (int cell : row) {
                    out.write(String.valueOf(cell).getBytes());
                    out.write(" ".getBytes());
                }
                out.write("\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                table[i - 1][j - 1] = i * j;
            }
        }
        return table;
    }
}

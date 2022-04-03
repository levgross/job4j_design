package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {


    public static void main(String[] args) {
        int size = 9;
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[][] table = new int[size][size];
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    table[i - 1][j - 1] = i * j;
                    out.write((String.format("%2d", table[i - 1][j - 1]) + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

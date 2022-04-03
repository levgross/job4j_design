package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) throws IOException {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            int num;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                num = Integer.parseInt(line);
                if (num % 2 == 0) {
                    System.out.println(num + " - is even");
                } else {
                    System.out.println(num + " - is odd");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
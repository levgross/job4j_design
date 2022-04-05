package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String[] array;
            String prev = "";
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                array = line.split(" ");
                if ("400".equals(array[0]) || "500".equals(array[0])) {
                        if (prev.startsWith("200") || prev.startsWith("300")) {
                            out.print(array[1] + ";");
                        }
                } else {
                    if ((prev.startsWith("400") || prev.startsWith("500"))) {
                        out.println(array[1] + ";");
                    }
                }
                prev = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis an = new Analysis();
        an.unavailable("server.log", "unavailable.csv");
    }
}

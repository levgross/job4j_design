package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> inList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            in.lines().forEach(inList :: add);
            String[] array;
            for (int i = 0; i < inList.size(); i++) {
                array = inList.get(i).split(" ");
                if ("400".equals(array[0]) || "500".equals(array[0])) {
                        if (i == 0 || inList.get(i - 1).startsWith("200") || inList.get(i - 1).startsWith("300")) {
                            out.print(array[1] + ";");
                        }
                } else {
                    if (i != 0 && (inList.get(i - 1).startsWith("400") || inList.get(i - 1).startsWith("500"))) {
                        out.println(array[1] + ";");
                    }
                }
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

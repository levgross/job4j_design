package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String dl = argsName.get("delimiter");

        String[] fields = argsName.get("filter").split(",");
        int[] fieldIndex = new int[fields.length];

        String output = argsName.get("out");
        File target = new File(output);
        int count = 1;
        try (Scanner scanner = new Scanner(new BufferedReader(
                new FileReader(path, StandardCharsets.UTF_8))).useDelimiter(dl)) {
            String[] firstLine = scanner.nextLine().split(dl);
            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < firstLine.length; j++) {
                    if (fields[i].equals(firstLine[j])) {
                        fieldIndex[i] = j;
                    }
                }
            }
        }
        if ("stdout".equals(output)) {
            try (Scanner scanner = new Scanner(new BufferedReader(
                    new FileReader(path, StandardCharsets.UTF_8))).useDelimiter(dl)) {
                    while (scanner.hasNextLine()) {
                        String[] array = scanner.nextLine().split(dl);
                        for (int i = 0; i < fieldIndex.length; i++) {
                            if (i == fieldIndex.length - 1) {
                                System.out.print(array[fieldIndex[i]]);
                            } else {
                                System.out.print(array[fieldIndex[i]] + dl);
                            }
                        }
                        System.out.println();
                    }
            }
        } else {
            try (Scanner scanner = new Scanner(new BufferedReader(
                    new FileReader(path, StandardCharsets.UTF_8))).useDelimiter(dl)) {
                try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
                    while (scanner.hasNextLine()) {
                        String[] array = scanner.nextLine().split(dl);
                        for (int i = 0; i < fieldIndex.length; i++) {
                            if (i == fieldIndex.length - 1) {
                                out.print(array[fieldIndex[i]]);
                            } else {
                                out.print(array[fieldIndex[i]] + dl);
                            }
                        }
                        out.println();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void validateArgs(ArgsName an) {
        String path = an.get("path");
        Path in = Paths.get(path);
        String output = an.get("out");
        if (!in.toFile().isFile() || !path.endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong input folder. "
                    + "Usage java -jar csvReader.jar -path=file.csv");
        }
        if (!"stdout".equals(output) && !output.endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong output argument."
                    + "Usage java -jar csvReader.jar -out=fileName.csv or -out=stdout");
        }
        an.get("delimiter");
        an.get("filter");
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        CSVReader csvReader = new CSVReader();
        csvReader.validateArgs(argsName);
        handle(argsName);
    }
}

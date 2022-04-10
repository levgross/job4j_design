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
        boolean printToFile = !"stdout".equals(output);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Scanner scanner = new Scanner(new BufferedReader(
                new FileReader(path, StandardCharsets.UTF_8))).useDelimiter(dl)) {
            try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
                while (scanner.hasNextLine()) {
                    String[] array = scanner.nextLine().split(dl);
                    for (int i = 0; i < fieldIndex.length; i++) {
                        if (i == fieldIndex.length - 1) {
                            printOutput(printToFile, out, array[fieldIndex[i]]);
                        } else {
                            printOutput(printToFile, out, array[fieldIndex[i]] + dl);
                        }
                    }
                    printOutput(printToFile, out, "\r\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printOutput(boolean printToFile, PrintWriter out, String el) {
        if (printToFile) {
            out.print(el);
        } else {
            System.out.print(el);
        }
    }

    private void validateArgs(ArgsName an) {
        String path = an.get("path");
        Path in = Paths.get(path);
        String output = an.get("out");
        if (!in.toFile().isFile() || !path.endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong input folder. Example: filename.csv "
                    + "Usage java -jar csvReader.jar -path=FILE_FOR_INPUT -out=FILE_FOR_OUTPUT (or -out=stdout) "
                    + "-delimiter=\"SYMBOL\" -filter=COLUMN_NAME_1,COLUMN_NAME_2");
        }
        if (!"stdout".equals(output) && !output.endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong output argument."
                    + "Usage java -jar csvReader.jar -path=FILE_FOR_INPUT -out=FILE_FOR_OUTPUT (or -out=stdout) "
                    + "-delimiter=\"SYMBOL\" -filter=COLUMN_NAME_1,COLUMN_NAME_2");
        }
        an.get("delimiter");
        an.get("filter");
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong quantity of arguments. "
                    + "Usage java -jar csvReader.jar -path=FILE_FOR_INPUT -out=FILE_FOR_OUTPUT (or -out=stdout) "
                    + "-delimiter=\"SYMBOL\" -filter=COLUMN_NAME_1,COLUMN_NAME_2");
        }
        ArgsName argsName = ArgsName.of(args);
        CSVReader csvReader = new CSVReader();
        csvReader.validateArgs(argsName);
        handle(argsName);
    }
}

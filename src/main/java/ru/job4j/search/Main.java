package ru.job4j.search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void handle(ArgsName argsName) throws IOException {
        Path root = Path.of(argsName.get("d"));
        String fileName = argsName.get("n");
        String typeSearch = argsName.get("t");
        Path target = Path.of(argsName.get("o"));
        List<Path> list = new ArrayList<>();
        if ("name".equals(typeSearch)) {
            list = search((root), p -> fileName.equals(p.toFile().getName()));
        } else if ("mask".equals(typeSearch)) {
            PathMatcher pm = FileSystems.getDefault().getPathMatcher("glob:" + fileName);
            list = search((root), p -> (pm.matches(p.getFileName())));
        } else if ("regex".equals(typeSearch)) {
            PathMatcher pm = FileSystems.getDefault().getPathMatcher("regex:" + fileName);
            list = search((root), p -> (pm.matches(p.getFileName())));
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(target.toString()))) {
            for (Path path : list) {
                bw.write(path.toString() + System.lineSeparator());
            }
        }
    }

    private static void validateArgs(ArgsName an) {
        Path root = Path.of(an.get("d"));
        String fileName = an.get("n");
        String typeSearch = an.get("t");
        if (!fileName.contains(".")) {
            throw new IllegalArgumentException("Wrong file name. "
                    + "Usage java -jar fileSearch.jar"
                    + "-d=ROOT_FILE "
                    + "-n=FILE_NAME_TO_SEARCH (it can be name, mask or regular expression) "
                    + "-t=TYPE_OF_SEARCH (name, mask or regex) "
                    + "-o=FILE_NAME_FOR_RESULT");
        }
        if (!"name".equals(typeSearch) && !"mask".equals(typeSearch) && !"regex".equals(typeSearch)) {
            throw new IllegalArgumentException("Wrong type of search argument."
                    + "Usage java -jar fileSearch.jar"
                    + "-d=ROOT_FILE "
                    + "-n=FILE_NAME_TO_SEARCH (it can be name, mask or regular expression) "
                    + "-t=TYPE_OF_SEARCH (name, mask or regex) "
                    + "-o=FILE_NAME_FOR_RESULT");
        }
        if (!root.toFile().isDirectory()) {
            throw new IllegalArgumentException("Wrong root file name. "
                    + "Usage java -jar fileSearch.jar"
                    + "-d=ROOT_FILE "
                    + "-n=FILE_NAME_TO_SEARCH (it can be name, mask or regular expression) "
                    + "-t=TYPE_OF_SEARCH (name, mask or regex) "
                    + "-o=FILE_NAME_FOR_RESULT");
        }
        an.get("o");
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong quantity of arguments. "
                    + "Usage java -jar fileSearch.jar"
                    + "-d=ROOT_FILE "
                    + "-n=FILE_NAME_TO_SEARCH (it can be name or mask) "
                    + "-t=TYPE_OF_SEARCH (name, mask) "
                    + "-o=FILE_NAME_FOR_RESULT");
        }
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        handle(argsName);
    }
}

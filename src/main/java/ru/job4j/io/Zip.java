package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateArgs(ArgsName an) {
        Path startPath = Paths.get(an.get("d"));
        if (!startPath.toFile().isDirectory()) {
            throw new IllegalArgumentException("Wrong root folder. "
                    + "Usage java -jar pack.jar -d=ROOT_FOLDER -e=EXTENSION_TO_EXCLUDE -o=TARGET_FOLDER");
        }
        if (!an.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Wrong extension to exclude from packing. It should start with a dot."
                    + "Usage java -jar pack.jar -d=ROOT_FOLDER -e=EXTENSION_TO_EXCLUDE -o=TARGET_FOLDER");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        zip.validateArgs(argsName);
        List<Path> searchList = Search.search(Paths.get(argsName.get("d")),
                p -> !p.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(searchList, Paths.get(argsName.get("o")));
    }
}
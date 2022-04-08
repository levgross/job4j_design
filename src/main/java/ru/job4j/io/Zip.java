package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zipFiles = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                zipFiles.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zipFiles.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            zip.putNextEntry(new ZipEntry(source.toString()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Not enough arguments. "
                    + "Usage java -jar pack.jar -d=ROOT_FOLDER -e=EXTENSION_TO_EXCLUDE -o=TARGET_FOLDER");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        Path start = Paths.get(argsName.get("d"));
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException("Wrong root folder. "
                    + "Usage java -jar pack.jar -d=ROOT_FOLDER -e=EXTENSION_TO_EXCLUDE -o=TARGET_FOLDER");
        }
        List<Path> searchList = Search.search(start, p -> !p.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(searchList, Paths.get(argsName.get("o")));
    }
}
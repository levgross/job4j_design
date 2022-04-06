package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor  extends SimpleFileVisitor<Path> {
    public Map<FileProperty, String> paths = new HashMap<>();
    public Set<String> duplicates = new HashSet<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fp = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (paths.containsKey(fp)) {
            duplicates.add(paths.get(fp));
            duplicates.add(file.toFile().getAbsolutePath());
        }
        paths.put(fp, file.toFile().getAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public Set<String> getDuplicates() {

        return duplicates;
    }
}

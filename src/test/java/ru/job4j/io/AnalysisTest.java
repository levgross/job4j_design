package ru.job4j.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalysisTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter in = new PrintWriter(source)) {
            in.println("200 10:56:01");
            in.println("500 10:57:01");
            in.println("400 10:58:01");
            in.println("200 10:59:01");
            in.println("500 11:01:02");
            in.println("200 11:02:02");
        }
        Analysis an = new Analysis();
        an.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader out = new BufferedReader(new FileReader(target))) {
            out.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01;" + "11:01:02;11:02:02;"));
    }

}
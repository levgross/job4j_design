package ru.job4j.io;


import org.junit.Test;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Lev Grossevich"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Lev Grossevich"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithEmptyLines() {
        String path = "./data/pair_with_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Lev Grossevich"));
        assertThat(config.value("age"), is("42"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenIllegalArgument() {
        String path = "./data/pair_with_illegal_argument.properties";
        Config config = new Config(path);
        config.load();
    }
}

package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = new ArrayList<>(simpleConvert.toList("one", "two", "three", "four", "five"));
        assertThat(list).hasSize(5)
                .contains("three")
                .contains("two", Index.atIndex(1))
                .containsAnyOf("zero", "one", "six")
                .doesNotContain("one", Index.atIndex(1));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = new HashSet<>(simpleConvert.toSet("one", "two", "three", "four", "five", "two", "four"));
        assertThat(set).hasSize(5)
                .containsAnyOf("ten", "nine", "four")
                .containsOnlyOnce("two", "four")
                .containsExactlyInAnyOrder("one", "two", "three", "four", "five")
                .doesNotContainNull()
                .doesNotContain("zero");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        var map = new HashMap<>(simpleConvert.toMap("one", "two", "three", "four", "five", "two", "four"));
        assertThat(map).hasSize(5)
                .containsKey("one")
                .containsKeys("two", "three")
                .doesNotContainKeys("nine", "ten")
                .containsValue(2)
                .containsValues(3, 0)
                .containsEntry("two", 1);
    }
}

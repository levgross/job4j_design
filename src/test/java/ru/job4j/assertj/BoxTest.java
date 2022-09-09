package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(1, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    public void whenGetNumberOfVerticesThen4() {
        var box = new Box(4, 1);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void whenGetNumberOfVerticesThenMinus1() {
        var box = new Box(1, 1);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void whenIsExistThenTrue() {
        var tetra = new Box(4, 10);
        boolean result = tetra.isExist();
        assertThat(result).isTrue();
    }

    @Test
    public void whenIsExistThenFalse() {
        var unknown = new Box(2, 10);
        boolean result = unknown.isExist();
        assertThat(result).isFalse();
    }

    @Test
    public void whenGetAreaThen600() {
        var cube = new Box(8, 10);
        double area = cube.getArea();
        assertThat(area)
                .isEqualTo(600d)
                .isEqualTo(600d, withPrecision(0.01d))
                .isCloseTo(600d, withPrecision(0.01d))
                .isCloseTo(600d, Percentage.withPercentage(1.0d))
                .isBetween(600d, 600.01d);
    }

    @Test
    public void whenGetAreaThen0() {
        var unknown = new Box(2, 10);
        double area = unknown.getArea();
        assertThat(area).isEqualTo(0d);
    }
}

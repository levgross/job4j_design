package ru.job4j.serialization.json;

import java.util.Arrays;

public class Dog {
    private final String name;
    private final boolean sex;
    private final int age;
    private final Parents parents;
    private final String[] food;

    public Dog(String name, boolean sex, int age, Parents parents, String[] food) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.parents = parents;
        this.food = food;
    }

    public String getName() {
        return name;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Parents getParents() {
        return parents;
    }

    public String[] getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Dog{"
                + "name=" + name
                + ", sex=" + sex
                + ", age=" + age
                + ", parents=" + parents
                + ", food=" + Arrays.toString(food)
                + '}';
    }
}

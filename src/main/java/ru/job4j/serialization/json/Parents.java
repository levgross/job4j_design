package ru.job4j.serialization.json;

public class Parents {
    String mother;
    String father;

    public Parents(String mother, String father) {
        this.mother = mother;
        this.father = father;
    }

    @Override
    public String toString() {
        return "Parents{"
                + "mother='" + mother + '\''
                + ", father='" + father + '\''
                + '}';
    }
}

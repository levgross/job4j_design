package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Dog dog = new Dog("Jack", false, 8,
                new Parents("Lila", "Bim"), new String[] {"Meat", "Dog food"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(dog));

        final String dogJson =
                "{"
                        + "\"name\":\"Jack\","
                        + "\"sex\":false,"
                        + "\"age\":8,"
                        + "\"parents\":"
                        + "{"
                        + "\"mother\":\"Lila\","
                        + "\"father\":\"Bim\""
                        + "},"
                        + "\"food\":"
                        + "[\"Meat\",\"Dog food\"]"
                        + "}";
        System.out.println(dogJson);
        final Dog dogMod = gson.fromJson(dogJson, Dog.class);
        System.out.println(dogMod);
    }
}

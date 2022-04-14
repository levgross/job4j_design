package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Dog dog = new Dog("Jack", false, 8,
                new Parents("Lila", "Bim"), new String[] {"Meat", "Dog food"});
        final Gson gson = new GsonBuilder().create();
        System.out.println("Gson to Json: " + gson.toJson(dog));

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
        System.out.println("Json string: " + dogJson);
        final Dog dogMod = gson.fromJson(dogJson, Dog.class);
        System.out.println("Gson to Java: " + dogMod);

        System.out.println("JSONObject Object: " + new JSONObject(dog));

        JSONObject jsonParents = new JSONObject("{\"mother\":\"Lila\",\"father\":\"Bim\"}");
        List<String> foodList = new ArrayList<>();
        foodList.add("Meat");
        foodList.add("Dog food");
        JSONArray jsonFood = new JSONArray(foodList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", dog.getName());
        jsonObject.put("sex", dog.isSex());
        jsonObject.put("age", dog.getAge());
        jsonObject.put("parents", jsonParents);
        jsonObject.put("food", jsonFood);

        System.out.println("JSONObject String: " + jsonObject);

    }
}

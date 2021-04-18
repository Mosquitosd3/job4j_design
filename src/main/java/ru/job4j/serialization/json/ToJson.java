package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ToJson {
    public static void main(String[] args) {
        final Owner owner = new Owner(false,1234, "Nikolay", new Pet("Caesar"), "+7(111)-111-11-11", "baker street 221b");
        final Gson gson = new GsonBuilder().create();
        final String ownerJson = gson.fromJson(gson.toJson(owner), Owner.class).toString();
        System.out.println(gson.toJson(owner));
        System.out.println(ownerJson);
    }
}

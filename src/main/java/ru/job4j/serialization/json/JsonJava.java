package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonJava {
    public static void main(String[] args) {
        final Owner owner = new Owner(false,1234, "Nikolay", new Pet("Caesar"), "+7(111)-111-11-11", "baker street 221b");
        JSONObject jsonAnimal = new JSONObject("{\"name\":\"Caesar\"}");

        String[] arr = owner.getOther();
        JSONArray jsonOther = new JSONArray(arr);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", owner.isSex());
        jsonObject.put("id", owner.getId());
        jsonObject.put("name", owner.getName());
        jsonObject.put("animal", jsonAnimal);
        jsonObject.put("others", jsonOther);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(owner).toString());

        Gson gson = new GsonBuilder().create();
        Owner outOwner = gson.fromJson(new JSONObject(owner).toString(), Owner.class);
        System.out.println(outOwner);
    }
}

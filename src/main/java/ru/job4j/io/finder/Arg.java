package ru.job4j.io.finder;

import java.util.HashMap;
import java.util.Map;

public class Arg {
    private Map<String, String> keys = new HashMap<>();
    
    public String get(String key) {
       return keys.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (String line : args) {
            String[] rsl = line.substring(1).split("=");
            if (rsl.length != 2) {
                throw new IllegalArgumentException();
            }
            keys.put(rsl[0], rsl[1]);
        }
        
    }

    public static Arg of(String[] args) {
        Arg arg = new Arg();
        arg.parse(args);
        return arg;
    }
}

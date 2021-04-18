package ru.job4j.serialization.json;

public class Owner {
    private final boolean sex;
    private final int id;
    private final String name;
    private final Pet animal;
    private final String[] other;

    public Owner(boolean sex, int id, String name, Pet animal, String... other) {
        this.sex = sex;
        this.id = id;
        this.name = name;
        this.animal = animal;
        this.other = other;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "sex=" + sex +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", animal=" + animal +
                '}';
    }
}

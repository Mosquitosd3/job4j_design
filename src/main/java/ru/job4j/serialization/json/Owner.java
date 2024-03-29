package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "owner")
@XmlAccessorType(XmlAccessType.FIELD)
public class Owner {
    @XmlAttribute
    private boolean sex;
    @XmlAttribute
    private int id;
    private String name;
    private Pet animal;

    @XmlElement(name = "other")
    @XmlElementWrapper(name = "otheres")
    private String[] other;

    public Owner(boolean sex, int id, String name, Pet animal, String... other) {
        this.sex = sex;
        this.id = id;
        this.name = name;
        this.animal = animal;
        this.other = other;
    }

    public boolean isSex() {
        return sex;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Pet getAnimal() {
        return animal;
    }

    public String[] getOther() {
        return other;
    }

    @Override
    public String toString() {
        return "Owner{"
                + "sex="
                + sex
                + ", id="
                + id
                + ", name='" + name + '\''
                + ", animal=" + animal
                + ", other=" + Arrays.toString(other)
                + '}';
    }
}

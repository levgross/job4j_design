package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "dog")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dog {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private boolean sex;

    @XmlAttribute
    private int age;

    private Parents parents;

    @XmlElementWrapper(name = "foods")
    @XmlElement(name = "food")
    private String[] food;

    public Dog() { }

    public Dog(String name, boolean sex, int age, Parents parents, String[] food) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.parents = parents;
        this.food = food;
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

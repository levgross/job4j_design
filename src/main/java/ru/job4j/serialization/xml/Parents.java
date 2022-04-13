package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "parents")
public class Parents {

    @XmlAttribute
    String mother;

    @XmlAttribute
    String father;

    public Parents() { }

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

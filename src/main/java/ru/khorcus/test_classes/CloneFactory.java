package ru.khorcus.test_classes;

public class CloneFactory {

    public CloneFactory() {
    }

    public Clone createClone(String name, int id, int power) {
        return new Clone(name, id, power);
    }
}

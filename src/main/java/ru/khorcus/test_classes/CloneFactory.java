package ru.khorcus.test_classes;

public class CloneFactory {

    public CloneFactory() {
    }

    public Clone createClone(String name, int id) {
        return new Clone(name, id);
    }
}

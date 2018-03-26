package ru.khorcus.test_classes;

import java.util.Objects;

public class Clone {
    private final String name;
    private final long id;
    private final int power;

    public Clone(String name, long id, int power) {
        this.name = name;
        this.id = id;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getPower() {
        return power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clone clone = (Clone) o;
        return id == clone.id &&
                power == clone.power &&
                Objects.equals(name, clone.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, power);
    }
}

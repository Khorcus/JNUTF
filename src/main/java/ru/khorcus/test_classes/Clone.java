package ru.khorcus.test_classes;

import java.util.Objects;

public class Clone {
    private final String name;
    private final long id;

    public Clone(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clone clone = (Clone) o;
        return id == clone.id &&
                Objects.equals(name, clone.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}

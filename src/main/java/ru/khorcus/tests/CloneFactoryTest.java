package ru.khorcus.tests;

import ru.khorcus.framework.annotations.Before;
import ru.khorcus.framework.annotations.Test;
import ru.khorcus.framework.core.Assert;
import ru.khorcus.test_classes.Clone;
import ru.khorcus.test_classes.CloneFactory;

public class CloneFactoryTest {
    private CloneFactory cloneFactory;

    @Before
    public void setUp() {
        cloneFactory = new CloneFactory();
    }

    @Test
    public void createCloneTest() {
        Clone expected = new Clone("Alex", 19);
        Clone actual = cloneFactory.createClone("Alex", 20);
        Assert.assertEquals(expected, actual);
    }
}

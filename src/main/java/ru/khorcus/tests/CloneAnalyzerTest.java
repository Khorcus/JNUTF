package ru.khorcus.tests;

import ru.khorcus.framework.annotations.Test;
import ru.khorcus.framework.core.Assert;
import ru.khorcus.test_classes.Clone;
import ru.khorcus.test_classes.CloneAnalyzer;

public class CloneAnalyzerTest {

    @Test
    public void whenPowerHigherThan100ReturnTrue() {
        Clone clone = new Clone("Alex", 19, 111);
        Assert.assertTrue(CloneAnalyzer.isPowerfulClone(clone));
    }
}

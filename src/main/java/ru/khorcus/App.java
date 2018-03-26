package ru.khorcus;

import ru.khorcus.framework.core.ClassRunner;
import ru.khorcus.framework.core.Jnutf;
import ru.khorcus.framework.runners.TestRunner;
import ru.khorcus.tests.CloneAnalyzerTest;
import ru.khorcus.tests.CloneFactoryTest;

import java.util.Arrays;

public class App
{
    public static void main( String[] args )
    {
        ClassRunner cloneAnalyzerTestRunner = new Jnutf(CloneAnalyzerTest.class);
        ClassRunner cloneFactoryTestRunner = new Jnutf(CloneFactoryTest.class);
        TestRunner runner = new TestRunner(Arrays.asList(cloneAnalyzerTestRunner, cloneFactoryTestRunner));
        runner.run();
    }
}

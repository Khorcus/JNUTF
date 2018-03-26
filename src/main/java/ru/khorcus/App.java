package ru.khorcus;

import ru.khorcus.framework.runners.TestRunner;
import ru.khorcus.tests.CloneFactoryTest;

public class App
{
    public static void main( String[] args )
    {
        TestRunner runner = new TestRunner(CloneFactoryTest.class);
        runner.run();
    }
}

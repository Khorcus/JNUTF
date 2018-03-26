package ru.khorcus.framework.runners;

import ru.khorcus.framework.core.ClassRunner;
import ru.khorcus.framework.core.Jnutf;
import ru.khorcus.framework.core.TestResult;
import ru.khorcus.framework.textui.Printer;
import ru.khorcus.framework.textui.ResultPrinter;

import java.lang.reflect.Method;

public class TestRunner implements Runner {

    private final ClassRunner classRunner;
    private final Printer printer;

    public TestRunner(ClassRunner classRunner, Printer printer) {
        this.classRunner = classRunner;
        this.printer = printer;
    }

    public TestRunner(Class<?> testClass) {
        classRunner = new Jnutf(testClass);
        printer = new ResultPrinter(System.out);
    }

    @Override
    public void run() {
        for (Method testMethod : classRunner.getTestMethods()) {
            long startTime = System.currentTimeMillis();
            TestResult result = classRunner.runTestMethod(testMethod);
            long endTime = System.currentTimeMillis();
            long runTime = endTime - startTime;
            printer.print(result, runTime);
        }
    }
}

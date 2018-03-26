package ru.khorcus.framework.runners;

import ru.khorcus.framework.core.ClassRunner;
import ru.khorcus.framework.core.Jnutf;
import ru.khorcus.framework.core.TestResult;
import ru.khorcus.framework.textui.Printer;
import ru.khorcus.framework.textui.ResultPrinter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner implements Runner {

    private List<ClassRunner> classRunnerList = new ArrayList<>();
    private ClassRunner classRunner;
    private final Printer printer;

    public TestRunner(ClassRunner classRunner, Printer printer) {
        this.classRunner = classRunner;
        this.printer = printer;
    }

    public TestRunner(List<ClassRunner> classRunnerList, Printer printer) {
        this.classRunnerList = classRunnerList;
        this.printer = printer;
    }

    public TestRunner(ClassRunner classRunner) {
        this.classRunner = classRunner;
        this.printer = new ResultPrinter(System.out);
    }

    public TestRunner(List<ClassRunner> classRunnerList) {
        this.classRunnerList = classRunnerList;
        this.printer = new ResultPrinter(System.out);
    }

    public TestRunner(Class<?> testClass) {
        this.classRunner = new Jnutf(testClass);
        this.printer = new ResultPrinter(System.out);
    }
    public TestRunner(Class<?> testClass, Printer printer) {
        this.classRunner = new Jnutf(testClass);
        this.printer = printer;
    }

    @Override
    public void run() {
        if (classRunner != null) {
            runTest(classRunner);
        } else {
            for (ClassRunner classRunner : classRunnerList) {
                runTest(classRunner);
            }
        }


    }

    protected void runTest(ClassRunner classRunner) {
        for (Method testMethod : classRunner.getTestMethods()) {
            long startTime = System.currentTimeMillis();
            TestResult result = classRunner.runTestMethod(testMethod);
            long endTime = System.currentTimeMillis();
            long runTime = endTime - startTime;
            printer.print(result, runTime);
        }
    }
}

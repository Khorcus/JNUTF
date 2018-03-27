package ru.khorcus.framework.textui;

import ru.khorcus.framework.core.TestResult;

import java.io.PrintStream;

public class ResultPrinter extends BasePrinter {

    private PrintStream fWriter;

    public ResultPrinter(PrintStream fWriter) {
        this.fWriter = fWriter;
    }

    protected void printHeader(TestResult result, long runTime) {
        fWriter.println();
        fWriter.println("@Test[" + result.getTestMethod() + "]");
        fWriter.println("Time: "+ elapsedTimeAsString(runTime));
    }

    protected void printResult(TestResult result) {
        if (result.wasSuccessful()) {
            fWriter.println();
            fWriter.println("OK: Run was successful");
        } else {
            fWriter.println();
            fWriter.println("FAILURES!!! Run was not successful");
            if (result.getFailure() != null) {
                AssertionError failure = result.getFailure();
                fWriter.println(failure.toString());
                fWriter.println(stackTraceToString(failure.getStackTrace()));
            } else if (result.getThrowable() != null) {
                Throwable throwable = result.getThrowable();
                fWriter.println(throwable.toString());
                fWriter.println(stackTraceToString(throwable.getStackTrace()));
            }

        }
        fWriter.println("--------------------------------");
    }

}

package ru.khorcus.framework.textui;

import ru.khorcus.framework.core.TestResult;

import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultPrinter implements Printer {

    private PrintStream fWriter;

    public ResultPrinter(PrintStream fWriter) {
        this.fWriter = fWriter;
    }

    @Override
    public void print(TestResult result, long runTime) {
        printHeader(result ,runTime);
        printResult(result);
    }

    protected void printHeader(TestResult result, long runTime) {
        fWriter.println();
        fWriter.println(result.getTestMethod());
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

    protected String elapsedTimeAsString(long runTime) {
        return NumberFormat.getInstance().format((double)runTime / 1000.0D);
    }

    protected String stackTraceToString(StackTraceElement[] stackTrace) {
        return Stream.of(stackTrace)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n", "[", "]"));
    }
}

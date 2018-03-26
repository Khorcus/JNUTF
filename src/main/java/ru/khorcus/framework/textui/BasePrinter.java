package ru.khorcus.framework.textui;

import ru.khorcus.framework.core.TestResult;

import java.text.NumberFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BasePrinter implements Printer {

    @Override
    public void print(TestResult result, long runTime) {
        printHeader(result ,runTime);
        printResult(result);
    }

    protected abstract void printHeader(TestResult result, long runTime);

    protected abstract void printResult(TestResult result);

    protected String elapsedTimeAsString(long runTime) {
        return NumberFormat.getInstance().format((double)runTime / 1000.0D);
    }

    protected String stackTraceToString(StackTraceElement[] stackTrace) {
        return Stream.of(stackTrace)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n", "[", "]"));
    }
}

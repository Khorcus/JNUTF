package ru.khorcus.framework.textui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khorcus.framework.core.TestResult;

public class Log4JPrinter extends BasePrinter{

    private final static Logger logger = LoggerFactory.getLogger(Log4JPrinter.class);
    StringBuilder stringBuilder = new StringBuilder();



    @Override
    protected void printHeader(TestResult result, long runTime) {
        stringBuilder.append("\n")
                .append("@Test[")
                .append(result.getTestMethod())
                .append("]\n")
                .append("Time: ")
                .append(elapsedTimeAsString(runTime))
                .append("\n");
    }

    @Override
    protected void printResult(TestResult result) {
        if (result.wasSuccessful()) {
            stringBuilder.append("\n")
                    .append("OK: Run was successful")
                    .append("\n")
                    .append("--------------------------------")
                    .append("\n");
            logger.info(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        } else {
            stringBuilder.append("\n")
                    .append("FAILURES!!! Run was not successful")
                    .append("\n");
            if (result.getFailure() != null) {
                AssertionError failure = result.getFailure();
                stringBuilder.append(failure.toString())
                        .append("\n")
                        .append(stackTraceToString(failure.getStackTrace()))
                        .append("\n");
            } else if (result.getThrowable() != null) {
                Throwable throwable = result.getThrowable();
                stringBuilder.append(throwable.toString())
                        .append("\n")
                        .append(stackTraceToString(throwable.getStackTrace()))
                        .append("\n");
            }
            stringBuilder.append("--------------------------------").append("\n");
            logger.error(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        }

    }
}

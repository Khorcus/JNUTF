package ru.khorcus.framework.textui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khorcus.framework.core.TestResult;

public class Log4JPrinter extends BasePrinter{

    private final static Logger logger = LoggerFactory.getLogger(Log4JPrinter.class);


    @Override
    protected void printHeader(TestResult result, long runTime) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n")
                .append(result.getTestMethod())
                .append("\n")
                .append("Time: ")
                .append(elapsedTimeAsString(runTime))
                .append("\n");
        logger.info(stringBuilder.toString());
    }

    @Override
    protected void printResult(TestResult result) {
        StringBuilder stringBuilder = new StringBuilder();
        if (result.wasSuccessful()) {
            stringBuilder.append("\n")
                    .append("OK: Run was successful")
                    .append("\n")
                    .append("--------------------------------")
                    .append("\n");
            logger.info(stringBuilder.toString());
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
        }

    }
}

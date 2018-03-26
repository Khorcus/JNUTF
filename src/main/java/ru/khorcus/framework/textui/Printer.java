package ru.khorcus.framework.textui;

import ru.khorcus.framework.core.TestResult;

public interface Printer {

    void print(TestResult result, long runTime);
}

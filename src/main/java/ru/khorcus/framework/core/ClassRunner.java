package ru.khorcus.framework.core;

import java.lang.reflect.Method;
import java.util.List;

public interface ClassRunner {

    TestResult runTestMethod(Method testMethod);

    List<Method> getTestMethods();

}

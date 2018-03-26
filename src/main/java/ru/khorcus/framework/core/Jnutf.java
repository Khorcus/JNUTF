package ru.khorcus.framework.core;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Jnutf extends JnutfClassRunner {

    public Jnutf(Class<?> testClass) {
        super(testClass);
    }

    @Override
    public TestResult runTestMethod(Method testMethod) {
        TestResult result = new TestResult(testMethod.getName());
        //TODO: collapse it
        try {
            Object instance = createTestInstance();
            if (getBefore() != null) {
                runMethod(getBefore(), instance);
            }
            try {
                try {
                    testMethod.invoke(instance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot access to method: " + testMethod.getName(), e);
                } catch (InvocationTargetException e) {
                    throw e.getTargetException();
                }
            } catch (Throwable e) {
                if (e instanceof AssertionError) {
                    result.setFailure((AssertionError) e);
                } else {
                    result.setThrowable(e);
                }
            }
            if (getAfter() != null) {
                runMethod(getAfter(), instance);
            }
        } catch (Throwable throwable) {
            result.setThrowable(throwable);
        }
        return result;
    }

    protected Object createTestInstance() {
        Object test;
        try {
            test = getConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot instantiate test case: " + getName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot access test case: " + getName(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Exception in constructor: " + getName(), e);
        }
        return test;
    }

    protected void runMethod(Method method, Object instance) {
        try {
            method.invoke(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot access to method: " + method.getName(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Cannot invoke method: " + method.getName(), e);
        }
    }
}
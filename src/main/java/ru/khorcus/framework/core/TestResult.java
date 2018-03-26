package ru.khorcus.framework.core;

public class TestResult {

    private String testMethod;
    //TODO: maybe replace with List<Throwable>
    private Throwable throwable;
    //TODO: maybe replace with List<AssertionError>
    private AssertionError failure;

    public TestResult(String testMethod) {
        this.testMethod = testMethod;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public void setFailure(AssertionError failure) {
        this.failure = failure;
    }

    public boolean wasSuccessful() {
        return throwable == null && failure == null;
    }

    public String getTestMethod() {
        return testMethod;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public AssertionError getFailure() {
        return failure;
    }
}

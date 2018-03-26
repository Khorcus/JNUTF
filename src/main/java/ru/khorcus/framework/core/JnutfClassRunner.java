package ru.khorcus.framework.core;

import ru.khorcus.framework.annotations.After;
import ru.khorcus.framework.annotations.Before;
import ru.khorcus.framework.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class JnutfClassRunner implements ClassRunner {

    private final Class<?> testClass;

    public JnutfClassRunner(Class<?> testClass) {
        this.testClass = testClass;
    }

    @Override
    public List<Method> getTestMethods() {
        return getAnnotatedMethods(Test.class);
    }

    protected Method getBefore() {
        List<Method> before = getAnnotatedMethods(Before.class);
        if (before.isEmpty()) {
            return null;
        }
        if (before.size() > 1) {
            throw new RuntimeException("Too many methods with @Before annotation");
        }
        return before.get(0);
    }

    protected Method getAfter() {
        List<Method> after = getAnnotatedMethods(After.class);
        if (after.isEmpty()) {
            return null;
        }
        if (after.size() > 1) {
            throw new RuntimeException("Too many methods with @After annotation");
        }
        return after.get(0);
    }

    protected Constructor<?> getConstructor() {
        Constructor<?> constructor;
        try {
            constructor = testClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Class: " + getName() + " has no public constructor", e);
        }
        return constructor;
    }

    protected String getName() {
        return testClass.getName();
    }

    protected List<Method> getAnnotatedMethods(Class<? extends Annotation> annotationClass) {
        return Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> method.getAnnotation(annotationClass) != null)
                .collect(Collectors.toList());
    }


}

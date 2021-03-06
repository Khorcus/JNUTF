package ru.khorcus.framework.core;

public class Assert {

    protected Assert() {
    }

    public static void assertTrue(boolean condition) {
        if (!condition) {
            fail();
        }
    }

    public static void assertFalse(boolean condition) {
        assertTrue(!condition);
    }

    public static void assertNotNull(Object object) {
        assertTrue(object != null);
    }

    public static void assertEquals(Object expected, Object actual) {
        if (equalsRegardingNull(expected, actual)) {
            return;
        } else {
            fail(format(expected, actual));
        }
    }

    public static void assertEquals(long expected, long actual) {
        if (expected != actual) {
            fail(format(expected, actual));
        }
    }

    public static void assertEquals(float expected, float actual, float delta) {
        if (floatIsDifferent(expected, actual, delta)) {
            fail(format(expected, actual));
        }
    }

    public static void assertEquals(double expected, double actual, double delta) {
        if (doubleIsDifferent(expected, actual, delta)) {
            fail(format(expected, actual));
        }
    }

    public static void fail(String message) {
        if (message == null) {
            throw new AssertionError();
        }
        throw new AssertionError(message);
    }

    public static void fail() {
        fail(null);
    }

    //TODO: write assertEquals for arrays

    protected static boolean doubleIsDifferent(double d1, double d2, double delta) {
        if (Double.compare(d1, d2) == 0) {
            return false;
        }
        return (Math.abs(d1 - d2) > delta);
    }

    protected static boolean floatIsDifferent(float d1, float d2, float delta) {
        if (Float.compare(d1, d2) == 0) {
            return false;
        }
        return (Math.abs(d1 - d2) > delta);
    }

    protected static boolean equalsRegardingNull(Object expected, Object actual) {
        if (expected == null) {
            return actual == null;
        }
        return isEquals(expected, actual);
    }

    protected static boolean isEquals(Object expected, Object actual) {
        return expected.equals(actual);
    }

    protected static String format(Object expected, Object actual) {
        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);
        if (equalsRegardingNull(expectedString, actualString)) {
            return "Expected: " + formatClassAndValue(expected, expectedString)
                    + " but was: " + formatClassAndValue(actual, actualString);
        } else {
            return "Expected: <" + expectedString
                    + "> but was: <" + actualString + ">";
        }
    }

    protected static String formatClassAndValue(Object value, String valueString) {
        String className = value == null ? "null" : value.getClass().getName();
        return className + "<" + valueString + ">";
    }
}

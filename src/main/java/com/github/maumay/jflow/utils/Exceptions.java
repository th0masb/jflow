/**
 *
 */
package com.github.maumay.jflow.utils;

/**
 * Static utility methods for throwing exceptions.
 *
 * @author ThomasB
 */
public final class Exceptions
{
    private Exceptions()
    {
    }

    /**
     * Checks that a given Iterable is reusable, i.e. calling
     * {@link Iterable#iterator()} multiple times results in different iterators. If
     * the iterable is not reusable then an exception will be thrown.
     *
     * @param iterable The input iterable.
     */
    public static void requireResuable(Iterable<?> iterable)
    {
        if (iterable.iterator() == iterable.iterator()) {
            throw new RuntimeException("The given iterable is not reusable.");
        }
    }

    /**
     * Checks a given condition is true, if it is then nothing happens otherwise a
     * {@linkplain RuntimeException} with no message is thrown.
     *
     * @param condition The condition which is required to be true.
     */
    public static void require(boolean condition)
    {
        require(condition, "");
    }

    /**
     * Checks a given condition is true, if it is then nothing happens otherwise a
     * {@linkplain RuntimeException} with the provided message is thrown.
     *
     * @param condition The condition which is required to be true.
     * @param message   The message which should be attached to the exception if it
     *                  is thrown.
     */
    public static void require(boolean condition, String message)
    {
        if (!condition) {
            throw new RuntimeException(message);
        }
    }

    /**
     * Checks a given condition is true, if it is then nothing happens otherwise a
     * {@linkplain IllegalArgumentException} with no message is thrown.
     *
     * @param condition The condition which is required to be true.
     */
    public static void requireArg(boolean condition)
    {
        requireArg(condition, "");
    }

    /**
     * Checks a given condition is true, if it is then nothing happens otherwise a
     * {@linkplain IllegalArgumentException} with the provided message is thrown.
     *
     * @param condition The condition which is required to be true.
     * @param message   The message which should be attached to the exception if it
     *                  is thrown.
     */
    public static void requireArg(boolean condition, String message)
    {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}

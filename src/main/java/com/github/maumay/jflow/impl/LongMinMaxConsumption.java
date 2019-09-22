/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.OptionalLong;

/**
 * @author ThomasB
 */
public class LongMinMaxConsumption
{
    private LongMinMaxConsumption()
    {
    }

    public static OptionalLong findMinOption(AbstractLongIterator source)
    {
        source.relinquishOwnership();
        boolean found = false;
        long min = Long.MAX_VALUE;
        while (source.hasNext()) {
            long next = source.nextLongImpl();
            if (!found) {
                found = true;
                min = next;
            } else if (next < min) {
                min = next;
            }
        }
        return found ? OptionalLong.of(min) : OptionalLong.empty();
    }

    public static long findMin(AbstractLongIterator source)
    {
        source.relinquishOwnership();
        boolean found = false;
        long min = Long.MAX_VALUE;
        while (source.hasNext()) {
            long next = source.nextLongImpl();
            if (!found) {
                found = true;
                min = next;
            } else if (next < min) {
                min = next;
            }
        }
        if (found) {
            return min;
        } else {
            throw new IllegalStateException();
        }
    }

    public static OptionalLong findMaxOption(AbstractLongIterator source)
    {
        source.relinquishOwnership();
        boolean found = false;
        long max = Long.MIN_VALUE;
        while (source.hasNext()) {
            long next = source.nextLongImpl();
            if (!found) {
                found = true;
                max = next;
            } else if (next > max) {
                max = next;
            }
        }
        return found ? OptionalLong.of(max) : OptionalLong.empty();
    }

    public static long findMax(AbstractLongIterator source)
    {
        source.relinquishOwnership();
        boolean found = false;
        long max = Long.MIN_VALUE;
        while (source.hasNext()) {
            long next = source.nextLongImpl();
            if (!found) {
                found = true;
                max = next;
            } else if (next > max) {
                max = next;
            }
        }
        if (found) {
            return max;
        } else {
            throw new IllegalStateException();
        }
    }
}

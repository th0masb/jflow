/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.function.LongPredicate;

/**
 * @author ThomasB
 */
public final class LongPredicateConsumption
{
    private LongPredicateConsumption()
    {
    }

    // public static boolean allEqual(AbstractLongIterator source)
    // {
    // source.relinquishOwnership();
    // boolean initialised = false;
    // long last = -1;
    // while (source.hasNext()) {
    // long next = source.nextLongImpl();
    // if (!initialised) {
    // initialised = true;
    // last = next;
    // } else if (last == next) {
    // last = next;
    // } else {
    // return false;
    // }
    // }
    // return true;
    // }

    public static boolean allMatch(AbstractLongIterator source,
            LongPredicate predicate)
    {
        source.relinquishOwnership();
        while (source.hasNext()) {
            if (!predicate.test(source.nextLongImpl())) {
                return false;
            }
        }
        return true;
    }

    public static boolean anyMatch(AbstractLongIterator source,
            LongPredicate predicate)
    {
        source.relinquishOwnership();
        while (source.hasNext()) {
            if (predicate.test(source.nextLongImpl())) {
                return true;
            }
        }
        return false;
    }

    public static boolean noneMatch(AbstractLongIterator source,
            LongPredicate predicate)
    {
        source.relinquishOwnership();
        while (source.hasNext()) {
            if (predicate.test(source.nextLongImpl())) {
                return false;
            }
        }
        return true;
    }
}

/**
 *
 */
package com.github.maumay.jflow.iterator;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

/**
 * Adds a method for safely retrieving the next element in a primitive int
 * iterator.
 *
 * @author t
 */
public interface OptionalIntIterator extends PrimitiveIterator.OfInt,
        IntSupplier
{
    /**
     * A safe alternative to directly calling {@link #nextInt()} method.
     *
     * @return An OptionalInt wrapping the next element if there is one.
     */
    OptionalInt nextIntOp();

    /**
     * Perform the supplied action for each element left in this iterator sequence,
     * in doing so the iterator is consumed.
     *
     * @param action The action to perform.
     */
    void forEach(IntConsumer action);

    @Override
    @Deprecated
    default Integer next()
    {
        throw new UnsupportedOperationException(
                "Boxing using this method is banned!!");
    }

    @Override
    default int getAsInt()
    {
        return nextIntOp().orElseThrow(
                () -> new NoSuchElementException("Supply has been exhausted"));
    }
}

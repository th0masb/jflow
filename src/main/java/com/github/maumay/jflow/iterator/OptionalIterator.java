/**
 *
 */
package com.github.maumay.jflow.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Adds a method for safely retrieving the next element in an iterator.
 *
 * @author t
 */
public interface OptionalIterator<E> extends Iterator<E>, Supplier<E>
{
    /**
     * A safe alternative to directly calling {@link Iterator#next()} method.
     *
     * @return An Optional wrapping the next element if there is one.
     */
    Optional<E> nextOp();

    /**
     * Perform the supplied action for each element left in this iterator sequence,
     * in doing so the iterator is consumed.
     *
     * @param action The action to perform.
     */
    void forEach(Consumer<? super E> action);

    @Override
    default E get()
    {
        return nextOp().orElseThrow(
                () -> new NoSuchElementException("Supply has been exhausted"));
    }
}

/**
 *
 */
package com.github.maumay.jflow.iterable;

import com.github.maumay.jflow.iterator.IntIterator;
import com.github.maumay.jflow.iterator.RichIterator;

import java.util.function.IntConsumer;

/**
 * Abstraction of iterable object which can construct enhanced primitive int
 * iterators ({@link IntIterator}).
 *
 * @author t
 */
@FunctionalInterface
public interface IntIterable extends Iterable<Integer>
{
    IntIterator iter();

    @Override
    default RichIterator<Integer> iterator()
    {
        return iter().boxed();
    }

    default void forEach(final IntConsumer action)
    {
        iter().forEachRemaining(action);
    }
}

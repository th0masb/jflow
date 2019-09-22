/**
 *
 */
package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.iterator.DoubleIterator;

/**
 * A custom adaption operation which can be inserted into an iterator chain via
 * {@link DoubleIterator#adapt(DoubleIteratorAdapter)}.
 *
 * @author thomasb
 */
@FunctionalInterface
public interface DoubleIteratorAdapter
{
    /**
     * Creates a new iterator adapter from the supplied source iterator. The
     * ownership of the source must be removed at some point in this method
     * implementation.
     *
     * @param source The iterator to adapt.
     * @return The adaption result.
     */
    DoubleIterator adapt(AbstractDoubleIterator source);
}

/**
 * 
 */
package com.github.maumay.jflow.iterators;

import com.github.maumay.jflow.impl.AbstractRichIterator;

/**
 * A custom adaption operation which can be inserted into an iterator chain via
 * {@link RichIterator#adapt(RichIteratorAdapter)}.
 * 
 * @param <E> The element type of the input iterators.
 * @param <R> The element type of the output iterators.
 * @author thomasb
 */
@FunctionalInterface
public interface RichIteratorAdapter<E, R>
{
	/**
	 * Creates a new iterator adapter from the supplied source iterator. The
	 * ownership of the source must be removed at some point in this method
	 * implementation.
	 * 
	 * @param source The iterator to adapt.
	 * @return The adaption result.
	 */
	AbstractRichIterator<R> adapt(AbstractRichIterator<? extends E> source);
}

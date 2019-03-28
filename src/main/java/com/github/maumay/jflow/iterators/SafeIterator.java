/**
 *
 */
package com.github.maumay.jflow.iterators;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Adds a method for safely retrieving the next element in an iterator.
 *
 * @author t
 */
public interface SafeIterator<E> extends Iterator<E>, Skippable
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
	void forEach(final Consumer<? super E> action);
}

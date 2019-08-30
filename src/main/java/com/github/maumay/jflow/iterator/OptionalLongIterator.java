/**
 *
 */
package com.github.maumay.jflow.iterator;

import java.util.NoSuchElementException;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;

/**
 * Adds a method for safely retrieving the next element in a primitive long
 * iterator.
 *
 * @author t
 */
public interface OptionalLongIterator extends PrimitiveIterator.OfLong, LongSupplier
{

	/**
	 * A safe alternative to directly calling {@link #nextLong()} method.
	 *
	 * @return An OptionalLong wrapping the next element if there is one.
	 */
	OptionalLong nextLongOp();

	/**
	 * Perform the supplied action for each element left in this iterator sequence,
	 * in doing so the iterator is consumed.
	 *
	 * @param action The action to perform.
	 */
	void forEach(LongConsumer action);

	@Override
	@Deprecated
	default Long next()
	{
		throw new UnsupportedOperationException(
				"Boxing using this method is banned for Flows!!");
	}

	@Override
	default long getAsLong()
	{
		return nextLongOp().orElseThrow(
				() -> new NoSuchElementException("Supply has been exhausted"));
	}
}

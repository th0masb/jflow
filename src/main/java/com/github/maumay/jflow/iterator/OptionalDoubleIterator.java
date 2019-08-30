/**
 *
 */
package com.github.maumay.jflow.iterator;

import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator.OfDouble;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

/**
 * Adds a method for safely retrieving the next element in a primitive double
 * iterator.
 *
 * @author t
 */
public interface OptionalDoubleIterator extends OfDouble, DoubleSupplier
{
	/**
	 * A safe alternative to directly calling {@link #nextDouble()} method.
	 *
	 * @return An OptionalDouble wrapping the next element if there is one.
	 */
	OptionalDouble nextDoubleOp();

	/**
	 * Perform the supplied action for each element left in this iterator sequence,
	 * in doing so the iterator is consumed.
	 *
	 * @param action The action to perform.
	 */
	void forEach(DoubleConsumer action);

	@Override
	@Deprecated
	default Double next()
	{
		throw new UnsupportedOperationException("Boxing using this method is banned!!");
	}

	@Override
	default double getAsDouble()
	{
		return nextDoubleOp().orElseThrow(
				() -> new NoSuchElementException("Supply has been exhausted"));
	}
}

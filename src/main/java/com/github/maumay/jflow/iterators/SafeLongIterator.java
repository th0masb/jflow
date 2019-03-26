/**
 *
 */
package com.github.maumay.jflow.iterators;

import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.function.LongConsumer;

/**
 * A precursor interface to {@linkplain LongIterator}.
 *
 * @author t
 */
public interface SafeLongIterator extends Skippable, PrimitiveIterator.OfLong// , OptionallySized
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
		throw new UnsupportedOperationException("Boxing using this method is banned for Flows!!");
	}
}

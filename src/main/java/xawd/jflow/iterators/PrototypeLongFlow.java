/**
 *
 */
package xawd.jflow.iterators;

import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.function.LongConsumer;

/**
 * An interface implementing the requisite interfaces for {@link LongFlow} and adding
 * some necessary but somewhat unrelated functionality.
 *
 * @author t
 */
public interface PrototypeLongFlow extends Skippable, PrimitiveIterator.OfLong
{
	/**
	 * A safe alternative to directly calling
	 * {@link PrimitiveIterator.OfLong#nextLong()} method.
	 *
	 * @return an {@link OptionalLong} wrapping the next element if there is one.
	 */
	default OptionalLong safeNextLong()
	{
		return hasNext() ? OptionalLong.of(nextLong()) : OptionalLong.empty();
	}

	/**
	 * Perform the supplied action for each element left in this iterator sequence,
	 * in doing so the iterator is consumed.
	 *
	 * @param action
	 *            - the action to perform.
	 */
	default void forEach(final LongConsumer action)
	{
		while (hasNext()) {
			action.accept(nextLong());
		}
	}

	@Override
	@Deprecated
	default Long next()
	{
		throw new UnsupportedOperationException("Boxing using this method is banned for Flows!!");
	}
}

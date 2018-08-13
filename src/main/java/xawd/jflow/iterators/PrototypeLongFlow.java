/**
 *
 */
package xawd.jflow.iterators;

import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.function.LongConsumer;

/**
 * A precursor interface to {@linkplain LongFlow}.
 *
 * @author t
 */
public interface PrototypeLongFlow extends Skippable, PrimitiveIterator.OfLong, OptionallySized
{
	/**
	 * A safe alternative to directly calling {@link #nextLong()} method.
	 *
	 * @return An OptionalLong wrapping the next element if there is one.
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
	 *            The action to perform.
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

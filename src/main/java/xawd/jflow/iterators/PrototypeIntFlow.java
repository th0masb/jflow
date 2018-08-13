/**
 *
 */
package xawd.jflow.iterators;

import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.IntConsumer;

/**
 * A precursor interface to {@linkplain IntFlow}.
 *
 * @author t
 */
public interface PrototypeIntFlow extends PrimitiveIterator.OfInt, Skippable, OptionallySized
{
	/**
	 * A safe alternative to directly calling {@link #nextInt()} method.
	 *
	 * @return An OptionalInt wrapping the next element if there is one.
	 */
	default OptionalInt safeNextInt()
	{
		return hasNext() ? OptionalInt.of(nextInt()) : OptionalInt.empty();
	}

	/**
	 * Perform the supplied action for each element left in this iterator sequence,
	 * in doing so the iterator is consumed.
	 *
	 * @param action
	 *            The action to perform.
	 */
	default void forEach(final IntConsumer action)
	{
		while (hasNext()) {
			action.accept(nextInt());
		}
	}

	@Override
	@Deprecated
	default Integer next()
	{
		throw new UnsupportedOperationException("Boxing using this method is banned for Flows!!");
	}
}

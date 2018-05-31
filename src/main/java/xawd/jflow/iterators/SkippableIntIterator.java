/**
 *
 */
package xawd.jflow.iterators;

import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.IntConsumer;

/**
 * @author t
 *
 */
public interface SkippableIntIterator extends PrimitiveIterator.OfInt, Skippable
{
	default OptionalInt safeNextInt()
	{
		return hasNext()? OptionalInt.of(nextInt()) : OptionalInt.empty();
	}

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
		throw new UnsupportedOperationException("No boxing!");
	}
}

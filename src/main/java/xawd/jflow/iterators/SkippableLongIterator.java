/**
 *
 */
package xawd.jflow.iterators;

import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.function.LongConsumer;

/**
 * @author t
 *
 */
public interface SkippableLongIterator extends Skippable, PrimitiveIterator.OfLong
{
	default OptionalLong safeNextLong()
	{
		return hasNext()? OptionalLong.of(nextLong()) : OptionalLong.empty();
	}

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
		throw new UnsupportedOperationException("No boxing!");
	}
}

/**
 *
 */
package xawd.jflow.iterators;

import java.util.PrimitiveIterator;
import java.util.function.LongConsumer;

/**
 * @author t
 *
 */
public interface SkippableLongIterator extends Skippable, PrimitiveIterator.OfLong
{
	default void forEach(final LongConsumer action)
	{
		while (hasNext()) {
			action.accept(nextLong());
		}
	}
	
	@Deprecated
	default Long next()
	{
		throw new UnsupportedOperationException("No boxing!");
	}
}

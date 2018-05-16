/**
 *
 */
package xawd.jflow.iterators.skippable;

import java.util.PrimitiveIterator;
import java.util.function.IntConsumer;

/**
 * @author t
 *
 */
public interface SkippableIntIterator extends PrimitiveIterator.OfInt, Skippable
{
	default void forEach(final IntConsumer action)
	{
		while (hasNext()) {
			action.accept(nextInt());
		}
	}
	
	@Deprecated
	default Integer next()
	{
		throw new UnsupportedOperationException("No boxing!");
	}
}

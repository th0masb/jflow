/**
 *
 */
package xawd.jflow.iterators;

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
}

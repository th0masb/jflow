/**
 *
 */
package xawd.jflow.iterators;

import java.util.PrimitiveIterator.OfDouble;
import java.util.function.DoubleConsumer;

/**
 * @author t
 *
 */
public interface SkippableDoubleIterator extends OfDouble, Skippable {

	default void forEach(final DoubleConsumer action)
	{
		while (hasNext()) {
			action.accept(nextDouble());
		}
	}
}

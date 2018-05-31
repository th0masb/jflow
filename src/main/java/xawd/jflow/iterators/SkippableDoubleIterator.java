/**
 *
 */
package xawd.jflow.iterators;

import java.util.OptionalDouble;
import java.util.PrimitiveIterator.OfDouble;
import java.util.function.DoubleConsumer;

/**
 * @author t
 *
 */
public interface SkippableDoubleIterator extends OfDouble, Skippable
{
	default OptionalDouble safeNextDouble()
	{
		return hasNext()? OptionalDouble.of(nextDouble()) : OptionalDouble.empty();
	}

	default void forEach(final DoubleConsumer action)
	{
		while (hasNext()) {
			action.accept(nextDouble());
		}
	}

	@Override
	@Deprecated
	default Double next()
	{
		throw new UnsupportedOperationException("No boxing!");
	}
}

/**
 *
 */
package xawd.jflow.iterators;

import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.PrimitiveIterator.OfDouble;
import java.util.function.DoubleConsumer;

/**
 * An interface implementing the requisite interfaces for {@link DoubleFlow} and adding
 * some necessary but somewhat unrelated functionality.
 *
 * @author t
 */
public interface PrototypeDoubleFlow extends OfDouble, Skippable
{
	/**
	 * A safe alternative to directly calling
	 * {@link PrimitiveIterator.OfDouble#nextDouble()} method.
	 *
	 * @return an {@link OptionalDouble} wrapping the next element if there is one.
	 */
	default OptionalDouble safeNextDouble()
	{
		return hasNext()? OptionalDouble.of(nextDouble()) : OptionalDouble.empty();
	}

	/**
	 * Perform the supplied action for each element left in this iterator sequence,
	 * in doing so the iterator is consumed.
	 *
	 * @param action
	 *            - the action to perform.
	 */
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
		throw new UnsupportedOperationException("Boxing using this method is banned for Flows!!");
	}
}

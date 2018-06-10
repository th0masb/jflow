/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import xawd.jflow.iterators.Skippable;

/**
 * @author ThomasB
 */
public final class ObjectReductionConsumption
{
	private ObjectReductionConsumption() {}

	public static <E> Optional<E> reduce(final Iterator<? extends E> source, final BinaryOperator<E> reducer)
	{
		E reduction = null;
		while (source.hasNext()) {
			if (reduction == null) {
				reduction = source.next();
			}
			else {
				reduction = reducer.apply(reduction, source.next());
			}
		}
		return reduction == null ? Optional.empty() : Optional.of(reduction);
	}

	public static <E, R> R reduce(final Iterator<? extends E> source, final R id, final BiFunction<R, E, R> reducer)
	{
		R reduction = id;
		while (source.hasNext()) {
			reduction = reducer.apply(reduction, source.next());
		}
		return reduction;
	}

	public static <E> long count(final Iterator<? extends E> source)
	{
		final boolean sourceSkippable = source instanceof Skippable;
		long count = 0;
		while (source.hasNext()) {
			if (sourceSkippable) {
				((Skippable) source).skip();
			}
			else {
				source.next();
			}
			count++;
		}
		return count;
	}
}

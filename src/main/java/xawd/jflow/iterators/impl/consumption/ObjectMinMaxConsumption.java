/**
 *
 */
package xawd.jflow.iterators.impl.consumption;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

/**
 * @author ThomasB
 *
 */
public final class ObjectMinMaxConsumption
{
	private ObjectMinMaxConsumption() {}

	public static <E> Optional<E> findMin(final Iterator<? extends E> source, final ToDoubleFunction<? super E> key)
	{
		boolean uninitialized = true;
		E min = null;
		double minVal = Double.POSITIVE_INFINITY;

		while (source.hasNext())
		{
			final E next = source.next();
			final double nextVal = key.applyAsDouble(next);

			if (uninitialized) {
				uninitialized = false;
				min = next;
				minVal = nextVal;
			}
			else {
				min = minVal <= nextVal ? min : next;
				minVal = next == min ? nextVal : minVal;
			}
		}
		return min == null ? Optional.empty() : Optional.of(min);
	}

	public static <E, C extends Comparable<C>> Optional<E> findMin(final Iterator<? extends E> source, final Function<? super E, C> key)
	{
		E min = null;
		C minVal = null;
		while (source.hasNext()) {
			final E next = source.next();
			final C nextVal = key.apply(next);

			if (min == null) {
				min = next;
				minVal = nextVal;
			}
			else {
				min = minVal.compareTo(nextVal) < 1 ? min : next;
				minVal = next == min ? nextVal : minVal;
			}
		}
		return min == null ? Optional.empty() : Optional.of(min);
	}

	public static <E> Optional<E> findMax(final Iterator<? extends E> source, final ToDoubleFunction<? super E> key)
	{
		boolean uninitialized = true;
		E max = null;
		double maxVal = Double.NEGATIVE_INFINITY;

		while (source.hasNext())
		{
			final E next = source.next();
			final double nextVal = key.applyAsDouble(next);

			if (uninitialized) {
				uninitialized = false;
				max = next;
				maxVal = nextVal;
			}
			else {
				max = maxVal >= nextVal ? max : next;
				maxVal = next == max ? nextVal : maxVal;
			}
		}
		return max == null ? Optional.empty() : Optional.of(max);
	}

	public static <E, C extends Comparable<C>> Optional<E> findMax(final Iterator<? extends E> source, final Function<? super E, C> key)
	{
		E max = null;
		C maxVal = null;
		while (source.hasNext()) {
			final E next = source.next();
			final C nextVal = key.apply(next);

			if (max == null) {
				max = next;
				maxVal = nextVal;
			}
			else {
				max = maxVal.compareTo(nextVal) > -1 ? max : next;
				maxVal = next == max ? nextVal : maxVal;
			}
		}
		return max == null ? Optional.empty() : Optional.of(max);
	}

	public static void main(final String[] args) {
		System.out.println(Double.POSITIVE_INFINITY > Double.POSITIVE_INFINITY);
	}
}

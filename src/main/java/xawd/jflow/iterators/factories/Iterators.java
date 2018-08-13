/**
 * 
 */
package xawd.jflow.iterators.factories;

import java.util.Iterator;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.impl.FlowFromIterator;

/**
 * Static methods for building Flows from existing iterators.
 * 
 * @author ThomasB
 */
public final class Iterators
{
	private Iterators()
	{
	}

	/**
	 * Wraps an existing iterator in a Flow to enable use of all extra
	 * functionality.
	 *
	 * @param src
	 *            The iterator to wrap.
	 * @return A flow wrapping the provided iterator.
	 */
	public static <E> Flow<E> wrap(Iterator<? extends E> src)
	{
		return new FlowFromIterator.OfObject<>(src);
	}

	/**
	 * Construct a Flow which wraps an iterator provided from an existing iterable
	 * object.
	 *
	 * @param <E>
	 *            The upper bound on the source iterable element type.
	 * @param src
	 *            An object which can construct an iterator over it's elements.
	 * @return A Flow instance wrapping an iterator constructed from the source
	 *         sequence.
	 */
	public static <E> Flow<E> wrap(Iterable<? extends E> src)
	{
		return wrap(src.iterator());
	}

}

/**
 *
 */
package xawd.jflow.iterators.construction;

import xawd.jflow.iterators.Flow;

/**
 * @author ThomasB
 *
 */
public final class IterRepeat {

	private IterRepeat() {}

	public static <E> Flow<E> of(final E element)
	{
		return Numbers.natural().mapToObject(i -> element);
	}
}

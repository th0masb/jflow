/**
 *
 */
package xawd.jflow.collections.impl;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author t
 *
 */
final class ImmutableListSpliterator<E> implements Spliterator<E>
{
	private final ImmutableFlowList<E> src;
	private final int upperBound;
	private int position;

	public ImmutableListSpliterator(ImmutableFlowList<E> src, int startPosition, int upperBound)
	{
		this.src = src;
		this.position = startPosition;
		this.upperBound = upperBound;
	}

	@Override
	public boolean tryAdvance(Consumer<? super E> action)
	{
		if (position < upperBound) {
			action.accept(src.get(position++));
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Spliterator<E> trySplit()
	{
		if (upperBound - position > 1) {
			final int midpoint = (upperBound + position) / 2;
			final int oldPosition = position;
			position = midpoint;
			return new ImmutableListSpliterator<>(src, oldPosition, midpoint);
		}
		else {
			return null;
		}
	}

	@Override
	public long estimateSize()
	{
		return upperBound - position;
	}

	@Override
	public int characteristics()
	{
		return IMMUTABLE | ORDERED | NONNULL | SIZED | SUBSIZED;
	}
}

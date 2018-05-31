/**
 *
 */
package xawd.jflow.iterators.iterables;

import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.DoubleConsumer;

import xawd.jflow.iterators.DoubleFlow;

/**
 * @author t
 *
 */
public interface DoubleFlowIterable
{
	DoubleFlow iterator();

	default void forEach(final DoubleConsumer action)
	{
		iterator().forEach(action);
	}

	/**
	 * Default behaviour is late-binding without any structural checks on the
	 * source.
	 */
	default Spliterator.OfDouble primitiveSpliterator()
	{
		final DoubleFlowIterable src = this;
		return new Spliterator.OfDouble()
		{
			PrimitiveIterator.OfDouble srcDoubles = null;

			@Override
			public long estimateSize()
			{
				return Long.MAX_VALUE;
			}

			@Override
			public int characteristics()
			{
				return 0;
			}

			@Override
			public OfDouble trySplit()
			{
				if (srcDoubles == null) {
					srcDoubles = src.iterator();
				}
				return null;
			}

			@Override
			public boolean tryAdvance(final DoubleConsumer action)
			{
				initialise();
				if (srcDoubles.hasNext()) {
					action.accept(srcDoubles.nextDouble());
					return true;
				}
				else {
					return false;
				}
			}

			@Override
			public void forEachRemaining(final DoubleConsumer action)
			{
				initialise();
				srcDoubles.forEachRemaining(action);
			}

			void initialise()
			{
				if (srcDoubles == null) {
					srcDoubles = src.iterator();
				}
			}
		};
	}
}

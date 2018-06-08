/**
 *
 */
package xawd.jflow.iterators.iterables;

import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.LongConsumer;

import xawd.jflow.iterators.LongFlow;

/**
 * @author t
 *
 */
public interface LongFlowIterable
{
	LongFlow iterator();

	default void forEach(final LongConsumer action)
	{
		iterator().forEachRemaining(action);
	}

	/*
	 * Default behaviour is late-binding without any structural checks on the
	 * source.
	 */
	default Spliterator.OfLong spliterator()
	{
		final LongFlowIterable src = this;
		return new Spliterator.OfLong()
		{
			PrimitiveIterator.OfLong srcLongs = null;

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
			public OfLong trySplit()
			{
				if (srcLongs == null) {
					srcLongs = src.iterator();
				}
				return null;
			}

			@Override
			public boolean tryAdvance(final LongConsumer action)
			{
				initialise();
				if (srcLongs.hasNext()) {
					action.accept(srcLongs.nextLong());
					return true;
				}
				else {
					return false;
				}
			}

			@Override
			public void forEachRemaining(final LongConsumer action)
			{
				initialise();
				srcLongs.forEachRemaining(action);
			}

			void initialise()
			{
				if (srcLongs == null) {
					srcLongs = src.iterator();
				}
			}
		};
	}

}

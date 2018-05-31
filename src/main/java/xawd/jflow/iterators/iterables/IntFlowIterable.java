/**
 *
 */
package xawd.jflow.iterators.iterables;

import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.IntConsumer;

import xawd.jflow.iterators.IntFlow;

/**
 * @author t
 *
 */
public interface IntFlowIterable
{
	IntFlow iterator();

	default void forEach(final IntConsumer action)
	{
		iterator().forEachRemaining(action);
	}

	/**
	 * Default behaviour is late-binding without any structural checks on the
	 * source.
	 */
	default Spliterator.OfInt primitiveSpliterator()
	{
		final IntFlowIterable src = this;
		return new Spliterator.OfInt()
		{
			PrimitiveIterator.OfInt srcInts = null;

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
			public OfInt trySplit()
			{
				if (srcInts == null) {
					srcInts = src.iterator();
				}
				return null;
			}

			@Override
			public boolean tryAdvance(final IntConsumer action)
			{
				initialise();
				if (srcInts.hasNext()) {
					action.accept(srcInts.nextInt());
					return true;
				}
				else {
					return false;
				}
			}

			@Override
			public void forEachRemaining(final IntConsumer action)
			{
				initialise();
				srcInts.forEachRemaining(action);
			}

			void initialise()
			{
				if (srcInts == null) {
					srcInts = src.iterator();
				}
			}
		};
	}
}

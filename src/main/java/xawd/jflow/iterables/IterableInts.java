/**
 *
 */
package xawd.jflow.iterables;

import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.IntConsumer;

import xawd.jflow.IntFlow;

/**
 * @author t
 *
 */
public interface IterableInts
{
	IntFlow iter();

	default void forEach(final IntConsumer action)
	{
		iter().forEachRemaining(action);
	}

	/**
	 * Default behaviour is late-binding without any structural checks on the
	 * source.
	 */
	default Spliterator.OfInt primitiveSpliterator()
	{
		final IterableInts src = this;
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
					srcInts = src.iter();
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
					srcInts = src.iter();
				}
			}
		};
	}
}
